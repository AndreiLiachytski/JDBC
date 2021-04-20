package com.example.utilits.mappers;

import com.example.commonInterfaces.MyFunction;
import com.example.model.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements MyFunction<ResultSet, User> {

    private UserMapper() {
    }

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