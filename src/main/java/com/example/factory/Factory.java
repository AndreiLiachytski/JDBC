package com.example.factory;

import com.example.dao.Dao;

public interface Factory {

    <T> Dao createDao(Class<T> myClass);

}
