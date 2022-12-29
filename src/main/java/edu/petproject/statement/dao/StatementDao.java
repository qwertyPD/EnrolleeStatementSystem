package edu.petproject.statement.dao;

import edu.petproject.statement.domain.StatementData;
import edu.petproject.statement.exception.DaoException;

import java.util.List;

public interface StatementDao {
    Long saveStatementData(StatementData sd) throws DaoException;

    List<StatementData> getStatementDataFromDB() throws DaoException;
}
