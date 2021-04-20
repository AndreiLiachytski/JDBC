package com.example.utilits.mappers;

import com.example.commonInterfaces.MyFunction;
import com.example.model.impl.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements MyFunction<ResultSet, Car> {

    private CarMapper() {
    }

    public static CarMapper getCarMapper() {
        return new CarMapper();
    }

    @Override
    public Car apply(final ResultSet resultSet) throws SQLException {
        return new Car.CarBuilder()
                .assignId(resultSet.getInt("id"))
                .assignName(resultSet.getString("name"))
                .assignPower(resultSet.getInt("power"))
                .build();

    }
}