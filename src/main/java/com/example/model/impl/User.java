package com.example.model.impl;

import com.example.model.Model;

public class User implements Model, Comparable<User> {
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(final User user) {
        if (name != null) {
            return name.compareTo(user.name);
        }
        return 1;
    }
}

