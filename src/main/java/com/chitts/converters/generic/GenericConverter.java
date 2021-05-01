package com.chitts.converters.generic;

import com.chitts.exceptions.AppException;
import com.chitts.model.Model;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface GenericConverter<T extends Model, R> {

    R convert(T t, Class<T> cl) throws JAXBException, AppException;

    R convert(List<T> list, Class<T> cl) throws JAXBException, AppException;
}