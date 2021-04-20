package com.example.utilits.convertors.XML;

import com.example.model.Model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.List;

public class XmlConvertor<T extends Model> {

    public String listObjectsAsXML(final List<T> list, final Class<T> cl) throws JAXBException, FileNotFoundException {
        final Model model = new Model();
        model.model = list;

        final StringWriter sw = new StringWriter();
        final JAXBContext jaxbContext = JAXBContext.newInstance(cl);
        final Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(model, sw);

        return sw.toString();
    }
}