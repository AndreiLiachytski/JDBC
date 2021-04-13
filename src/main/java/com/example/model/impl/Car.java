package com.example.model.impl;

import com.example.model.Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@JsonAutoDetect
public class Car extends Model implements Comparable<Car> {

    private int id;
    private String name;
    private int power;

    public Car() {
    }

    private Car(final CarBuilder builder) {
        this();
        id = builder.id;
        name = builder.name;
        power = builder.power;
    }

    public static class CarBuilder {
        private int id = 0;
        private String name = null;
        private int power = 0;

        public CarBuilder assignId(final int value) {
            id = value;
            return this;
        }

        public CarBuilder assignName(final String value) {
            name = value;
            return this;
        }

        public CarBuilder assignPower(final int value) {
            power = value;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
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
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(final Car obj) {
        if (name != null) {
            return name.compareTo(obj.name);
        }
        return 1;
    }
}