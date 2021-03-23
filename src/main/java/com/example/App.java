package com.example;

import com.example.config.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.impl.UserDao;
import com.example.model.impl.User;

import java.io.IOException;
import java.sql.SQLException;

public class App {

    public static void main(final String[] args) {
        try (final DataBaseConnection connection = DataBaseConnection.getInstance()) {

            final Dao<User> dao = new UserDao(connection.getConnection());
            dao.getAll().forEach(System.out::println);
            dao.sortingByName().forEach(System.out::println);

        } catch (final IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}
