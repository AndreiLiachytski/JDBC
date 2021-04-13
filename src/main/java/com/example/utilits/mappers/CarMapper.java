package com.example.utilits.mappers;

import com.example.model.impl.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CarMapper implements Function<ResultSet, Car> {
    private CarMapper() {
    }

    public static CarMapper getCarMapper() {
        return new CarMapper();
    }

    @Override
    public Car apply(final ResultSet resultSet) {
        Car car = null;
        try {
            car = new Car.CarBuilder()
                    .assignId(resultSet.getInt("id"))
                    .assignName(resultSet.getString("name"))
                    .assignPower(resultSet.getInt("power"))
                    .build();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return car;
    }
}