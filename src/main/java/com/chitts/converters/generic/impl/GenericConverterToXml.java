package com.chitts.converters.generic.impl;

import com.chitts.converters.generic.GenericConverter;
import com.chitts.model.Model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class GenericConverterToXml<T extends Model> implements GenericConverter<T, String> {

    public Marshaller getMarshaller(final Class<T> cl) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(cl);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Override
    public String convert(final T t, final Class<T> cl) throws JAXBException {
        final StringWriter stringWriter = new StringWriter();
        getMarshaller(cl).marshal(t, stringWriter);
        return stringWriter.toString();
    }

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JAXBException {
        final Model model = new Model();
        model.model = list;
        final StringWriter stringWriter = new StringWriter();
        getMarshaller(cl).marshal(model, stringWriter);
        return stringWriter.toString();
    }
}