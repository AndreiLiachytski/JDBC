package com.example.commonInterfaces;

import java.sql.SQLException;

@FunctionalInterface
public interface MyFunction<T, R> {

    R apply(T t) throws SQLException;
}
