package com.example;

import com.example.configs.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.daoImpl.GenericDao;
import com.example.model.impl.Car;
import com.example.model.impl.User;
import com.example.utilits.convertors.JsonConvertor;
import com.example.utilits.convertors.XmlConvertor;
import com.example.utilits.mappers.CarMapper;
import com.example.utilits.mappers.UserMapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class App {
    public static void main(final String[] args) {

//        try {
//            final Function<ResultSet, User> userMapper = new UserMapper();
//            final Function<ResultSet, Car> carMapper = new CarMapper();
//
//            final Dao<User> userDao = new GenericDao<>(DataBaseConnection.getInstance().getConnection(), "users", userMapper);
//            final Dao<Car> carDao = new GenericDao<>(DataBaseConnection.getInstance().getConnection(), "cars", carMapper);
//
//            final List<User> users = userDao.getAll();
//            final List<Car> cars = carDao.getAll();
//
//            final JsonConvertor<User> userToJsonConvertor = new JsonConvertor<>();
//            final JsonConvertor<Car> CarToJsonConvertor = new JsonConvertor<>();
//
//            System.out.println(userToJsonConvertor.getJsonArray(users, User.class));
//            System.out.println(CarToJsonConvertor.getJsonArray(cars, Car.class));
//
//            final XmlConvertor<User> userConvertor = new XmlConvertor<>();
//            final XmlConvertor<Car> carConvertor = new XmlConvertor<>();
//
//            System.out.println(userConvertor.listObjectsAsXML(users, User.class));
//            System.out.println(carConvertor.listObjectsAsXML(cars, Car.class));
//
//        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | JAXBException ex) {
//            ex.printStackTrace();
//        }
    }
}