package com.example.configs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection implements AutoCloseable {

    private final Connection connection;

    public DataBaseConnection() throws IOException, SQLException {
        this.connection = createConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    private static Properties getFileProperties() throws IOException {
        final Properties properties = new Properties();
        final File file = new File("H:/Projekts/JDBS/src/main/resources/config.properties");

        try (final FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
            return properties;
        }
    }

    private static Connection createConnection() throws SQLException, IOException {
        final Properties properties = getFileProperties();
        final String url = properties.getProperty("db.url");
        final String username = properties.getProperty("db.userName");
        final String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}