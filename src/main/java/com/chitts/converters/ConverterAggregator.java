package com.chitts.converters;

import com.chitts.converters.generic.GenericConverter;
import com.chitts.converters.generic.impl.GenericConverterToJson;
import com.chitts.converters.generic.impl.GenericConverterToXml;
import com.chitts.exceptions.ConverterException;
import com.chitts.model.Model;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterAggregator<T extends Model> {

    private final Map<String, GenericConverter<T, String>> genericConvertors = new HashMap<>();

    {
        genericConvertors.put("xml", new GenericConverterToXml<>());
        genericConvertors.put("json", new GenericConverterToJson<>());
    }

    private GenericConverter<T, String> getTypeConverter(final String typeConvertor) throws ConverterException {
        final GenericConverter<T, String> converter = genericConvertors.get(typeConvertor.toLowerCase());

        if (converter == null) {
            throw new ConverterException(typeConvertor.toUpperCase() + " type is not supported for conversion.");
        }
        return converter;
    }

    public String convertTo(final String typeConvertor, final T obj, final Class<T> cl) throws JAXBException, ConverterException {
        final GenericConverter<T, String> converter = getTypeConverter(typeConvertor);
        return converter.convert(obj, cl);
    }

    public String convertTo(final String typeConvertor, final List<T> list, final Class<T> cl) throws JAXBException, ConverterException {
        final GenericConverter<T, String> converter = getTypeConverter(typeConvertor);
        return converter.convert(list, cl);
    }
}