package com.example.dao;

import com.example.model.Model;

import java.sql.SQLException;
import java.util.List;

public interface Dao {

    List<Model> getAll() throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    List<Model> sortingByName() throws SQLException;

}
