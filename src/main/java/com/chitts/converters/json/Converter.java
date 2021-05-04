package com.chitts.converters.json;

import com.chitts.model.Model;

import java.util.List;

public interface Converter<T extends Model, R> {

    R convert(T t);

    R convert(List<T> list);

}