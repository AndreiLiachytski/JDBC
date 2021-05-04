package com.chitts.configs;

import com.chitts.exceptions.DataBaseConnectionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection implements AutoCloseable {

    private final Connection connection;

    public DataBaseConnection() throws DataBaseConnectionException {
        this.connection = createConnection();
    }

    private static Properties getFileProperties() throws DataBaseConnectionException {
        final Properties properties = new Properties();
        final File file = new File("H:/Projekts/JDBS/src/main/resources/config.properties");

        try (final FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
            return properties;
        } catch (final FileNotFoundException e) {
            throw new DataBaseConnectionException("Cannot find config.properties file.", e);
        } catch (final IOException e) {
            throw new DataBaseConnectionException("Cannot load config.properties file.", e);
        }
    }

    private static Connection createConnection() throws DataBaseConnectionException {
        final Properties properties = getFileProperties();
        final String url = properties.getProperty("db.url");
        final String username = properties.getProperty("db.userName");
        final String password = properties.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (final SQLException throwable) {
            throw new DataBaseConnectionException("Cannot get DataBase connection.", throwable);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}