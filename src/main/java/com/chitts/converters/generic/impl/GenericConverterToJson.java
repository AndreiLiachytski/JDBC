package com.chitts.converters.generic.impl;

import com.chitts.converters.generic.GenericConverter;
import com.chitts.converters.json.Converter;
import com.chitts.converters.json.impl.CarConverterToJson;
import com.chitts.converters.json.impl.UserConverterToJson;
import com.chitts.exceptions.ConverterException;
import com.chitts.model.Model;
import com.chitts.model.impl.Car;
import com.chitts.model.impl.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericConverterToJson<T extends Model> implements GenericConverter<T, String> {

    private final Map<Class<?>, Converter<T, String>> convertersToJson = new HashMap<>();

    {
        convertersToJson.put(User.class, (Converter<T, String>) new UserConverterToJson());
        convertersToJson.put(Car.class, (Converter<T, String>) new CarConverterToJson());
    }

    private Converter<T, String> getClassConverter(final Class<? extends Model> cl) throws ConverterException {
        final Converter<T, String> converter = convertersToJson.get(cl);
        final String className = cl.getName();

        if (converter == null) {
            throw new ConverterException(className + " class is not supported for conversion.");
        }
        return converter;
    }

    @Override
    public String convert(final T t, final Class<T> cl) throws ConverterException {
        final Converter<T, String> converter = getClassConverter(cl);
        return converter.convert(t);
    }

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws ConverterException {
        final Converter<T, String> converter = getClassConverter(cl);
        return converter.convert(list);
    }
}