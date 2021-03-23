package com.example.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private static Connection connection;

    private final String url;
    private final String userName;
    private final String password;


    private DataBaseConnection() {
        url = getFileProperties().getProperty("db.url");
        userName = getFileProperties().getProperty("db.userName");
        password = getFileProperties().getProperty("db.password");
    }

    private Properties getFileProperties() {
        Properties properties = new Properties();
        File file = new File("src/main/resources/config.properties");
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DataBaseConnection();
            try {
                connection = DriverManager.getConnection(instance.getUrl(), instance.getUserName(), instance.getPassword());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {
            System.out.println("Connection created already");
        }
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}





