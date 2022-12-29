package edu.petproject.statement.dao;

import edu.petproject.statement.domain.EducationOffice;
import edu.petproject.statement.domain.PassportOffice;
import edu.petproject.statement.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {
    private static final Logger logger = LogManager.getLogger(DictionaryDaoImpl.class);

    private static final String GET_EDU_OFFICE = "SELECT edu_office_id, edu_office_name " +
        "FROM education_office WHERE UPPER(edu_office_name) LIKE UPPER(?)";
    private static final String GET_PASS_OFFICE = "SELECT pass_office_id, pass_office_name " +
            "FROM passport_office WHERE pass_office_id = ?";

    private Connection getConn() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public List<EducationOffice> findEducationalOffice(String text) throws DaoException {
        List<EducationOffice> result = new LinkedList<>();

        try (Connection conn = getConn();
             PreparedStatement stmt = conn.prepareStatement(GET_EDU_OFFICE);) {

            stmt.setString(1, "%" + text + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EducationOffice eo = new EducationOffice(rs.getInt("edu_office_id"), rs.getString("edu_office_name"));
                result.add(eo);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }

    public List<PassportOffice> findPassportOffice(int passOfficeId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection conn = getConn();
            PreparedStatement stmt = conn.prepareStatement(GET_PASS_OFFICE);) {

            stmt.setInt(1, passOfficeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PassportOffice po = new PassportOffice(rs.getInt(1), rs.getString(2));
                result.add(po);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }

        return result;
    }
}
