package com.chitts.model.impl;

import com.chitts.model.Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonAutoDetect
public class Car extends Model implements Comparable<Car> {

    private final int id;
    private final String name;
    private final int power;

    public Car() {
        this.id = 0;
        this.name = null;
        this.power = 0;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "power")
    public int getPower() {
        return power;
    }

    @Override
    public int compareTo(final Car obj) {
        if (name != null) {
            return name.compareTo(obj.name);
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}