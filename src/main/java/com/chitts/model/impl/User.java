package com.chitts.model.impl;

import com.chitts.model.Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonAutoDetect
public class User extends Model implements Comparable<User> {

    private final int id;
    private final String name;
    private final String email;

    public User() {
        this.id = 0;
        this.name = null;
        this.email = null;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "E-mail")
    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(final User user) {
        if (name != null) {
            return name.compareTo(user.name);
        }
        return 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}