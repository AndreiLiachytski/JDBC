package com.chitts.model.builder.extractors;

import com.chitts.model.Model;

import java.lang.reflect.Field;

public class ClassFieldsExtractor<T extends Model> {

    private final Class<T> cl;

    public ClassFieldsExtractor(final Class<T> cl) {
        this.cl = cl;
    }

    public Field[] getFields() {
        return cl.getDeclaredFields();
    }
}