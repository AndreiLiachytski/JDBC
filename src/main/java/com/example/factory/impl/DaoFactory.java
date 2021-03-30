package com.example.factory.impl;

import com.example.dao.Dao;
import com.example.dao.daoImpl.CarDao;
import com.example.dao.daoImpl.UserDao;
import com.example.factory.Factory;
import com.example.model.impl.Car;
import com.example.model.impl.User;

import java.sql.Connection;

public class DaoFactory implements Factory {

    private final Connection connection;

    public DaoFactory(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> Dao createDao(final Class<T> myClass) {
        Dao dao = null;
        if (myClass.equals(User.class)) {
            dao = new UserDao(connection);
        }
        if (myClass.equals(Car.class)) {
            dao = new CarDao(connection);
        }

        return dao;
    }
}
