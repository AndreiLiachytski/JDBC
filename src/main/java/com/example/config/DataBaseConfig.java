package com.example.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfig {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            Properties properties = new Properties();
            File file = new File("src/main/resources/config.properties");
            try {
                properties.load(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            final String url = properties.getProperty("db.url");
            final String userName = properties.getProperty("db.userName");
            final String password = properties.getProperty("db.password");
            final String driver = properties.getProperty("db.driver");
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {
            System.out.println("Connection created already");
        }
        return connection;
    }
}
