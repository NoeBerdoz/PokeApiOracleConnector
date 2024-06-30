package ch.nb;
import ch.nb.utils.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnector {
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("database.properties");

    private static final String DB_URL = propertiesLoader.getProperty("db.url");
    private static final String DB_USER = propertiesLoader.getProperty("db.user");
    private static final String DB_PASSWORD = propertiesLoader.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        System.out.println("[+] Connecting to database...");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
