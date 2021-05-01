package com.chitts.mappers.impl;

import com.chitts.model.impl.Car;
import com.chitts.mappers.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements Mapper<ResultSet, Car> {

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