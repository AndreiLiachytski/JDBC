package com.chitts.utilits.converters.impl.json;

import com.chitts.exceptions.ClassConverterException;
import com.chitts.model.Model;
import com.chitts.model.impl.Car;
import com.chitts.model.impl.User;
import com.chitts.utilits.converters.Converter;
import org.json.JSONException;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericConverterToJson<T extends Model> implements Converter<T, String> {

    private Map<Class<?>, Converter<T, String>> getConvertorsToJson() {
        final Map<Class<?>, Converter<T, String>> convertorsToJson = new HashMap<>();
        convertorsToJson.put(User.class, new UserConverterToJson<>());
        convertorsToJson.put(Car.class, new CarConverterToJson<>());

        return convertorsToJson;
    }

    private Converter<T, String> getClassConverter(final Class<T> cl) throws ClassConverterException {
        final Converter<T, String> converter = getConvertorsToJson().get(cl);
        if (converter == null) {
            final String className = cl.getName();
            throw new ClassConverterException(className + " class is not supported for conversion.");
        }
        return converter;
    }

    @Override
    public String convert(final T t, final Class<T> cl) throws JSONException, ClassConverterException, JAXBException {
        final Converter<T, String> converter = getClassConverter(cl);

        return converter.convert(t, cl);
    }

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JAXBException, ClassConverterException {
        final Converter<T, String> converter = getClassConverter(cl);

        return converter.convert(list, cl);
    }
}