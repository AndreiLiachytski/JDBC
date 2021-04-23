package com.chitts.utilits.converters.impl.xml;

import com.chitts.exceptions.ClassConverterException;
import com.chitts.model.Model;
import com.chitts.utilits.converters.Converter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class GenericConverterToXml<T extends Model> implements Converter<T, String> {

    private StringWriter stringWriter;

    public Marshaller getMarshaller(final Class<T> cl) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(cl);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        return marshaller;
    }

    @Override
    public String convert(final T t, final Class<T> cl) throws JAXBException {
        stringWriter = new StringWriter();
        getMarshaller(cl).marshal(t, stringWriter);

        return stringWriter.toString();
    }

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JAXBException, ClassConverterException {
        final Model model = new Model();
        model.model = list;
        stringWriter = new StringWriter();
        getMarshaller(cl).marshal(model, stringWriter);

        return stringWriter.toString();
    }
}