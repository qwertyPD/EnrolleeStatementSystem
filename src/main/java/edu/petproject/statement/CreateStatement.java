package edu.petproject.statement;

import edu.petproject.statement.dao.DictionaryDaoImpl;
import edu.petproject.statement.dao.StatementDaoImpl;
import edu.petproject.statement.domain.*;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CreateStatement {

    public static long saveStatement() {
        long answer = 11;
        return answer;
    }
}
