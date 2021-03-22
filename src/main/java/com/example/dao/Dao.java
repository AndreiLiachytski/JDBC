package com.example.dao;

import com.example.model.Model;

import java.util.List;

public interface Dao {
    List<Model> getAll();

    List<Model> sortingByName();

}
