package edu.petproject.statement.dao;

import edu.petproject.statement.domain.*;
import edu.petproject.statement.exception.DaoException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

public class StatementDaoImplTest {

    @BeforeClass
    public static void startUp() throws URISyntaxException, IOException {
        InitDatabase.startUp();
    }

    @Test
    public void saveStatementData() throws DaoException {
        StatementData sd = buildStatement(11);
        Long statementID = new StatementDaoImpl().saveStatementData(sd);
        System.out.println(statementID);
    }

    @Test(expected = DaoException.class)
    public void saveStatementDataError() throws DaoException {
        StatementData sd = buildStatement(12);
        sd.getPerson().setName(null);
        Long statementID = new StatementDaoImpl().saveStatementData(sd);
    }

    @Test
    public void getStatementDataFromDB() throws DaoException {
        List<StatementData> sd = new StatementDaoImpl().getStatementDataFromDB();
    }

    public static StatementData buildStatement(int id) {
        StatementData sd = new StatementData();
        EducationOffice eduOffice = new EducationOffice();
        sd.setStatementDataId(id);

        // Создание заявителя
        Person person = new Person("Antonov", "Anton", "Antonovich");
        // Создание паспорта
        PassportOffice po = new PassportOffice(1, "");
        Passport passport = new Passport("Antonov", "Anton", "Antonovich",
                1000 + id, 100000 + id, "11-01-2009");
        passport.setPassportOffice(po);
        // Создание дипломов
        EducationOffice eo = new EducationOffice(23, "");
        Diploma diploma1 = new Diploma("Antonov", "Anton", "Antonovich",
                2000 + id, 200000 + id, "20-07-2015");
        Diploma diploma2 = new Diploma("Antonov", "Anton", "Antonovich",
                3000 + id, 300000 + id, "22-06-2017");

        diploma1.setEducationOffice(eo);
        diploma2.setEducationOffice(eo);

        sd.setPerson(person);
        sd.setPassport(passport);
        sd.addDiploma(diploma1);
        sd.addDiploma(diploma2);

        return sd;
    }
}