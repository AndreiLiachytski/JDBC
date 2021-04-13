package com.example.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, JsonProcessingException;

    List<T> getAllSortedByName() throws SQLException;

}
