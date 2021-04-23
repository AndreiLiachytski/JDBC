package com.chitts.utilits.converters;

import com.chitts.exceptions.ClassConverterException;
import com.chitts.model.Model;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface Converter<T extends Model, R> {

    R convert(T t, Class<T> cl) throws JAXBException, ClassConverterException;

    R convert(List<T> list, Class<T> cl) throws JAXBException, ClassConverterException;

}