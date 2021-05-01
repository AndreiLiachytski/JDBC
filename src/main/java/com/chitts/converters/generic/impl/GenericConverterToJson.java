package com.chitts.converters.generic.impl;

import com.chitts.converters.generic.GenericConverter;
import com.chitts.converters.json.Converter;
import com.chitts.converters.json.impl.CarConverterToJson;
import com.chitts.converters.json.impl.UserConverterToJson;
import com.chitts.exceptions.AppException;
import com.chitts.exceptions.converter.childs.ClassConverterException;
import com.chitts.model.Model;
import com.chitts.model.impl.Car;
import com.chitts.model.impl.User;
import org.json.JSONException;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericConverterToJson<T extends Model> implements GenericConverter<T, String> {

    private Map<Class<?>, Converter<T, String>> getConvertorsToJson() {
        final Map<Class<?>, Converter<T, String>> convertorsToJson = new HashMap<>();
        convertorsToJson.put(User.class, (Converter<T, String>) new UserConverterToJson());
        convertorsToJson.put(Car.class, (Converter<T, String>) new CarConverterToJson());
        return convertorsToJson;
    }

    private Converter<T, String> getClassConverter(final Class<? extends Model> cl) throws AppException {
        final Converter<T, String> converter = getConvertorsToJson().get(cl);
        final String className = cl.getName();
        try {
            if (converter == null) {
                throw new ClassConverterException();
            }
        } catch (final ClassConverterException e) {
            throw new AppException(className + " class is not supported for conversion.", e);
        }
        return converter;
    }

    @Override
    public String convert(final T t, final Class<T> cl) throws JSONException, AppException, JAXBException {
        final Converter<T, String> converter = getClassConverter(cl);
        return converter.convert(t);
    }

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JAXBException, AppException {
        final Converter<T, String> converter = getClassConverter(cl);
        return converter.convert(list);
    }
}