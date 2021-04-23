package com.chitts.utilits.mappers.impl;

import com.chitts.model.impl.User;
import com.chitts.utilits.mappers.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<ResultSet, User> {

    public static UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Override
    public User apply(final ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .assignId(resultSet.getInt("id"))
                .assignName(resultSet.getString("name"))
                .assignEmail(resultSet.getString("email"))
                .build();
    }
}