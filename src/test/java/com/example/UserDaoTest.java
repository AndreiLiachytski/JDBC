package com.example;

import com.example.model.Model;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoTest {
    @Test
    public List<Model> getAll() {
        List<Model> users = new ArrayList<>();
        return users;
    }

    @Test
    public List<Model> sortingByName() {
        return getAll().stream().sorted().collect(Collectors.toList());
    }
}
