package com.chitts.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "models")
public class Model {

    public List<? extends Model> model = new ArrayList<>();

}