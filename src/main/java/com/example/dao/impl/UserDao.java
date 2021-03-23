package com.example.dao.impl;

import com.example.dao.Dao;
import com.example.model.impl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao implements Dao<User> {

    private final Connection connection;

    public UserDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users")) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final List<User> users = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                final User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }

            return users;
        }
    }

    @Override
    public List<User> sortingByName() throws SQLException {
        return getAll().stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
