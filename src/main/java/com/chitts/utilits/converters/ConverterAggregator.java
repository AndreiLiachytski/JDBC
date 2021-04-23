package com.chitts.utilits.converters;

import com.chitts.exceptions.ClassConverterException;
import com.chitts.exceptions.TypeConverterException;
import com.chitts.model.Model;
import com.chitts.utilits.converters.impl.json.GenericConverterToJson;
import com.chitts.utilits.converters.impl.xml.GenericConverterToXml;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ConverterAggregator<T extends Model> {

    private Converter<T, String> converter;

    private Map<String, Converter<T, String>> getGenericConvertors() {
        final Map<String, Converter<T, String>> genericConvertors = new HashMap<>();
        genericConvertors.put("xml", new GenericConverterToXml<>());
        genericConvertors.put("json", new GenericConverterToJson<>());

        return genericConvertors;
    }

    private Converter<T, String> getTypeConverter(final String typeConvertor) throws TypeConverterException {
        converter = getGenericConvertors().get(typeConvertor.toLowerCase(Locale.ROOT));
        if (converter == null) {
            throw new TypeConverterException(typeConvertor + " type is not supported for conversion.");
        }
        return converter;
    }

    public String convertTo(final String typeConvertor, final T obj, final Class<T> cl) throws JAXBException, TypeConverterException, ClassConverterException {
        converter = getTypeConverter(typeConvertor);
        return converter.convert(obj, cl);
    }

    public String convertTo(final String typeConvertor, final List<T> list, final Class<T> cl) throws JAXBException, TypeConverterException, ClassConverterException {
        converter = getTypeConverter(typeConvertor);
        return converter.convert(list, cl);
    }
}