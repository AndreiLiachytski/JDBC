package com.chitts.model.impl;

import com.chitts.model.Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonAutoDetect
public class User extends Model implements Comparable<User> {

    private int id;
    private String name;
    private String email;

    public User() {
    }

    private User(final UserBuilder builder) {
        this();
        id = builder.id;
        name = builder.name;
        email = builder.email;
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

    public static class UserBuilder {
        private int id = 0;
        private String name = null;
        private String email = null;

        public UserBuilder assignId(final int value) {
            id = value;
            return this;
        }

        public UserBuilder assignName(final String value) {
            name = value;
            return this;
        }

        public UserBuilder assignEmail(final String value) {
            email = value;
            return this;
        }

        public User build() {
            return new User(this);
        }
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