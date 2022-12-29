package edu.petproject.statement.dao;

import edu.petproject.statement.domain.EducationOffice;
import edu.petproject.statement.domain.PassportOffice;
import edu.petproject.statement.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DictionaryDaoImplTest {

    private static final Logger logger = LogManager.getLogger(DictionaryDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws URISyntaxException, IOException {
        InitDatabase.startUp();
    }

    @Test
    public void findEducationalOfficeTest() throws DaoException {
        LocalDateTime ldt = LocalDateTime.now();
        logger.info("TEST {}", ldt);

        List<EducationOffice> listEduOffice = new DictionaryDaoImpl().findEducationalOffice("ОУ");
        Assert.assertEquals(3, listEduOffice.size());
    }

    @Test
    public void findPassportOfficeTest() throws DaoException {
        List<PassportOffice> listPassOffice = new DictionaryDaoImpl().findPassportOffice(3);
        Assert.assertEquals(1, listPassOffice.size());
    }
}