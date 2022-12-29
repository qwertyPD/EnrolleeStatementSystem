package edu.petproject.statement.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class InitDatabase {
    public static void startUp() throws URISyntaxException, IOException {
        URL urlCreateTables = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("enrollee_documents.sql");
        URL urlFillTables = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("enrollee_documents_data.sql");

        List<String> sqlCreateTablesStrs = Files.readAllLines(Paths.get(urlCreateTables.toURI()));
        List<String> sqlFillTablesStrs = Files.readAllLines(Paths.get(urlFillTables.toURI()));

        String sqlCreateTable = sqlCreateTablesStrs.stream().collect(Collectors.joining());
        String sqlFillTables = sqlFillTablesStrs.stream().collect(Collectors.joining());

        try (Connection conn = ConnectionBuilder.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreateTable);
            stmt.executeUpdate(sqlFillTables);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
