package com.chitts.dao.impl;

import com.chitts.dao.Dao;
import com.chitts.model.Model;
import com.chitts.mappers.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericDao<T extends Model> implements Dao<T> {

    private final Connection connection;
    private final String sql;
    private final Mapper<ResultSet, T> mapper;

    public GenericDao(final Connection connection, final String tableName, final Mapper<ResultSet, T> mapper) {
        this.connection = connection;
        this.sql = "SELECT * FROM " + tableName;
        this.mapper = mapper;
    }

    @Override
    public List<T> getAll() throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<T> models = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
                final T model = mapper.apply(resultSet);
                models.add(model);
            }
            return models;
        }
    }

    @Override
    public List<T> getAllSortedByName() throws SQLException {
        return getAll().stream()
                .sorted()
                .collect(Collectors.toList());
    }
}