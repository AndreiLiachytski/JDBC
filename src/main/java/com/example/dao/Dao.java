package com.example.dao;

import com.example.model.Model;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Model> {

    List<T> getAll() throws SQLException;

    List<T> sortingByName() throws SQLException;

}
