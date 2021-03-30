package com.example.dao.daoImpl;

import com.example.dao.Dao;
import com.example.model.Model;
import com.example.model.impl.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarDao implements Dao {
    private final Connection connection;

    public CarDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Model> getAll() throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CARS")) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Model> cars = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                final Model car = new Car.CarBuilder()
                        .assignId(resultSet.getInt("id"))
                        .assignName(resultSet.getString("name"))
                        .assignPower(resultSet.getInt("power"))
                        .build();
                cars.add(car);
            }
            return cars;
        }
    }

    @Override
    public List<Model> sortingByName() throws SQLException {
        return getAll().stream()
                .sorted()
                .collect(Collectors.toList());
    }

}


