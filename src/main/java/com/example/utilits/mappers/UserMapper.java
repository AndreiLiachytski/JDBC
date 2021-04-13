package com.example.utilits.mappers;

import com.example.model.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class UserMapper implements Function<ResultSet, User> {

    private UserMapper() {
    }

    public static UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Override
    public User apply(final ResultSet resultSet) {
        User user = null;
        try {
            user = new User.UserBuilder()
                    .assignId(resultSet.getInt("id"))
                    .assignName(resultSet.getString("name"))
                    .assignEmail(resultSet.getString("email"))
                    .build();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}