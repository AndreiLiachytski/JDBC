package com.example.dao.impl;

import com.example.config.DataBaseConfig;
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

    private final Connection connection = DataBaseConfig.getConnection();

    public List<Model> getAll() {
        List<Model> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    public List<Model> sortingByName() {
        return getAll().stream().sorted().collect(Collectors.toList());
    }


}
