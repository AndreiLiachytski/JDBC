package com.chitts.dao;

import com.chitts.model.Model;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Model> {

    List<T> getAll() throws SQLException;

    List<T> getAllSortedByName() throws SQLException;

}