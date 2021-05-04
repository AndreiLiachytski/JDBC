package com.chitts.dao.impl;

import com.chitts.configs.DataBaseConnection;
import com.chitts.dao.Dao;
import com.chitts.exceptions.AppException;
import com.chitts.exceptions.DataBaseConnectionException;
import com.chitts.exceptions.GenericDaoException;
import com.chitts.model.Model;
import com.chitts.model.builder.impl.GenericBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericDao<T extends Model> implements Dao<T> {

    private final Class<T> cl;
    private final Connection connection;
    final GenericBuilder<T> genericBuilder;
    private final String sql;

    public GenericDao(final Class<T> cl) throws DataBaseConnectionException {
        this.cl = cl;
        this.connection = new DataBaseConnection().getConnection();
        this.genericBuilder = new GenericBuilder<>(cl);
        this.sql = "SELECT * FROM " + cl.getSimpleName().concat("s").toLowerCase();
    }

    @Override
    public List<T> getAll() throws AppException {
        final List<T> models = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final T model = cl.newInstance();
                genericBuilder.build(model, resultSet);
                models.add(model);
            }
        } catch (final SQLException throwable) {
            throw new GenericDaoException("Check SQL query in GenericDao.class.", throwable);
        } catch (final IllegalAccessException | InstantiationException e) {
            throw new GenericDaoException("Cannot create newInstance.", e);
        }
        return models;
    }

    @Override
    public List<T> getAllSortedByName() throws AppException {
        return getAll().stream()
                .sorted()
                .collect(Collectors.toList());
    }
}