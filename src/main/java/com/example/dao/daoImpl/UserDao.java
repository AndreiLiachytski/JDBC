package com.example.dao.daoImpl;

import com.example.dao.Dao;
import com.example.model.Model;
import com.example.model.impl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao implements Dao {
    private final Connection connection;

    public UserDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Model> getAll() throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users")) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Model> models = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                final Model user = new User.UserBuilder()
                        .assignId(resultSet.getInt("id"))
                        .assignName(resultSet.getString("name"))
                        .assignEmail(resultSet.getString("email"))
                        .build();
                models.add(user);
            }
            return models;
        }
    }

    @Override
    public List<Model> sortingByName() throws SQLException {
        return getAll().stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
