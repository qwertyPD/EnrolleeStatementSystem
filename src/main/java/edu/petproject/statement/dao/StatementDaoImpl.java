package edu.petproject.statement.dao;

import edu.petproject.statement.domain.*;
import edu.petproject.statement.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementDaoImpl implements StatementDao{
    private static final Logger logger = LogManager.getLogger(StatementDaoImpl.class);
    private static final String INSERT_STATEMENT =
            "INSERT INTO enrollee_statement( " +
            "statement_status, statement_date, enr_name, enr_surname, enr_patronymic, " +
            "passport_seria, passport_number, passport_office_id) " +
            "VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_DIPLOMAS =
            "INSERT INTO enrollee_statement_diploma( " +
            "enrollee_statement_id, education_seria, education_number, education_office_id) " +
            "VALUES (?, ?, ?, ?);";
    private static final String GET_STATEMENT_FROMDB =
            "SELECT * FROM enrollee_statement " +
            "JOIN passport_office ON enrollee_statement.passport_office_id = passport_office.pass_office_id " +
            "WHERE statement_status = 0 " +
            "ORDER BY statement_date;";
    private static final String GET_DIPLOMAS_FROMDB =
            "SELECT diploma_id, enrollee_statement_id, education_seria, education_number, education_office, edu_office_id, edu_office_name " +
                    "FROM enrollee_statement_diploma " +
                    "JOIN education_office ON education_office.edu_office_id = enrollee_statement_diploma.education_office_id " +
                    "WHERE enrollee_statement_id IN ";
    private Connection getConn() throws SQLException {
        return ConnectionBuilder.getConnection();
    }
    public Long saveStatementData(StatementData sd) throws DaoException {
        Long result = -1L;

        logger.debug("Statement Data: ", sd);

        try (Connection conn = getConn();
             PreparedStatement stmt = conn.prepareStatement(INSERT_STATEMENT, new String[]{"enr_order_id"});) {
            conn.setAutoCommit(false);
            try {
                // header
                stmt.setInt(1, StatementStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                // person
                stmt.setString(3, sd.getPerson().getName());
                stmt.setString(4, sd.getPerson().getSurname());
                stmt.setString(5, sd.getPerson().getPatronymic());
                // passport
                stmt.setInt(6, sd.getPassport().getSeria());
                stmt.setInt(7, sd.getPassport().getNumber());
                stmt.setInt(8, sd.getPassport().getPassportOffice().getPassOfficeId());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong("enr_order_id");
                }

                saveStatementDiploma(conn, sd, result);
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }
    private void saveStatementDiploma(Connection conn, StatementData sd, Long sdID) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_DIPLOMAS)) {
            // diploma
            for (Diploma d: sd.getDiploma()) {
                stmt.setLong(1, sdID);
                stmt.setInt(2, d.getSeria());
                stmt.setInt(3, d.getNumber());
                stmt.setInt(4, d.getEducationOffice().getEduOfficeId());

                stmt.executeUpdate();
            }
        }
    }
    public List<StatementData> getStatementDataFromDB() throws DaoException {
        LinkedList<StatementData> result = new LinkedList<>();

        try (Connection conn = getConn();
             PreparedStatement stmt = conn.prepareStatement(GET_STATEMENT_FROMDB)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StatementData sd = new StatementData();
                fillHeadersFromDB(rs, sd);
                fillPersonFromDB(rs, sd);
                fillPassportFromDB(rs, sd);

                result.add(sd);
            }

            getDiplomasFromDB(conn, result);

            rs.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }
    public void getDiplomasFromDB(Connection conn, List<StatementData> result) throws SQLException {
        String cl = "(" + result.stream().map(sd -> String.valueOf(sd.getStatementDataId()))
                .collect(Collectors.joining(",")) + ");";
        Map<Long, StatementData> maps = result.stream().collect(Collectors
                .toMap(sd -> sd.getStatementDataId(), sd -> sd));
        try (PreparedStatement stmt = conn.prepareStatement(GET_DIPLOMAS_FROMDB + cl)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Diploma dp = fillDiplomaFromDB(rs);
                StatementData sd = maps.get(rs.getLong("enrollee_statement_id"));
                sd.addDiploma(dp);
            }
        }
    }

    private void fillHeadersFromDB(ResultSet rs, StatementData sd) throws SQLException {
        sd.setStatementDataId(rs.getLong("enr_order_id"));
        sd.setStatementStatus(StatementStatus.fromValue(rs.getInt("statement_status")));
        sd.setStatementDate(rs.getTimestamp("statement_date").toLocalDateTime());
    }

    private void fillPersonFromDB(ResultSet rs, StatementData sd) throws SQLException {
        Person person = new Person();
        person.setName(rs.getString("enr_name"));
        person.setSurname(rs.getString("enr_surname"));
        person.setPatronymic(rs.getString("enr_patronymic"));

        sd.setPerson(person);
    }
    private void fillPassportFromDB(ResultSet rs, StatementData sd) throws SQLException {
        Passport passport = new Passport();
        passport.setSeria(rs.getInt("passport_seria"));
        passport.setNumber(rs.getInt("passport_number"));

        PassportOffice po = new PassportOffice(rs.getInt("passport_office_id"), rs.getString("pass_office_name"));
        passport.setPassportOffice(po);

        sd.setPassport(passport);
    }

    private Diploma fillDiplomaFromDB(ResultSet rs) throws SQLException {
        Diploma diploma = new Diploma();
        diploma.setSeria(rs.getInt("education_seria"));
        diploma.setNumber(rs.getInt("education_number"));

        EducationOffice eo = new EducationOffice(rs.getInt("edu_office_id"), rs.getString("edu_office_name"));
        diploma.setEducationOffice(eo);

        return diploma;
    }
}
