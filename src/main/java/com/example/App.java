package com.example;

import com.example.dao.impl.UserDao;
import com.example.model.Model;

import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Model> list;
        UserDao dao = new UserDao();

        list = dao.getAll();
        list.forEach(System.out::println);
        System.out.println();

        list = dao.sortingByName();
        list.forEach(System.out::println);
    }
}
