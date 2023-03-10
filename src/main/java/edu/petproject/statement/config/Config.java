package edu.petproject.statement.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASS = "db.pass";

    private static Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if (name.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("dao.properties")) {
                properties.load(is);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
            return properties.getProperty(name);
    }
}
