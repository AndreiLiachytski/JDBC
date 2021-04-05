package com.example;

import com.example.config.DataBaseConnection;
import com.example.dao.Dao;
import com.example.factory.impl.DaoFactory;
import com.example.model.impl.Car;
import com.example.model.impl.User;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(final String[] args) {
//
//        try (final DataBaseConnection connection = DataBaseConnection.getInstance()) {
//
//            final DaoFactory daoFactory = new DaoFactory(connection.getConnection());
//            final Dao userDao = daoFactory.createDao(User.class);
//            final Dao carDao = daoFactory.createDao(Car.class);
//
//            userDao.getAll().forEach(System.out::println);
//            userDao.sortingByName().forEach(System.out::println);
//            carDao.getAll().forEach(System.out::println);
//            carDao.sortingByName().forEach(System.out::println);
//
//        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
//            ex.printStackTrace();
//        }
    }
}
