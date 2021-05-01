package com.chitts.converters.json;

import com.chitts.exceptions.converter.childs.ClassConverterException;
import com.chitts.model.Model;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface Converter<T extends Model, R> {

    R convert(T t) throws JAXBException , ClassConverterException;

    R convert(List<T> list) throws JAXBException , ClassConverterException;

}