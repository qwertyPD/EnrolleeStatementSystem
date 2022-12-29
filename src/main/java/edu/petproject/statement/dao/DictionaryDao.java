package edu.petproject.statement.dao;

import edu.petproject.statement.domain.EducationOffice;
import edu.petproject.statement.domain.PassportOffice;
import edu.petproject.statement.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    public List<EducationOffice> findEducationalOffice(String text) throws DaoException;
    public List<PassportOffice> findPassportOffice(int passportOfficeid) throws DaoException;
}
