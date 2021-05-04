package com.chitts.dao;

import com.chitts.exceptions.AppException;
import com.chitts.model.Model;

import java.util.List;

public interface Dao<T extends Model> {

    List<T> getAll() throws AppException;

    List<T> getAllSortedByName() throws AppException;

}