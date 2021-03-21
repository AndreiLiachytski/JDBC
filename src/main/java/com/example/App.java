package com.example;

import com.example.dao.UserDao;
import com.example.model.User;

import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {

        UserDao dao = new UserDao();
        List<User> list = dao.getAll();
        list.stream().sorted(Comparator.comparing(User::getName)).forEach(System.out::println);
        //  list.stream().sorted( (user1, user2)->user1.getName().compareTo(user2.getName())).forEach(System.out::println);
        
    }
}
