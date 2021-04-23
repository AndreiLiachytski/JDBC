package com.chitts.utilits.mappers;

import java.sql.SQLException;

@FunctionalInterface
public interface Mapper<T, R> {

    R apply(T t) throws SQLException;
}