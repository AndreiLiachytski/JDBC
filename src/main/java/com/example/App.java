package com.example;

import com.example.configs.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.daoImpl.GenericDao;
import com.example.model.impl.Car;
import com.example.model.impl.User;
import com.example.utilits.convertors.XML.XmlConvertor;
import com.example.utilits.convertors.json.JsonConvertorAggregator;
import com.example.utilits.mappers.CarMapper;
import com.example.utilits.mappers.UserMapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(final String[] args) {

        try (final Connection connection = new DataBaseConnection().getConnection()) {

            final Dao<User> userDao = new GenericDao<>(connection, "users", UserMapper.getUserMapper());
            final Dao<Car> carDao = new GenericDao<>(connection, "cars", CarMapper.getCarMapper());

            final List<User> users = userDao.getAll();
            final List<Car> cars = carDao.getAll();

            System.out.println(JsonConvertorAggregator.getJsonArray(users, User.class));
            System.out.println(JsonConvertorAggregator.getJsonArray(cars, Car.class));

            final XmlConvertor<User> userConvertor = new XmlConvertor<>();
            final XmlConvertor<Car> carConvertor = new XmlConvertor<>();

            System.out.println(userConvertor.listObjectsAsXML(users, User.class));
            System.out.println(carConvertor.listObjectsAsXML(cars, Car.class));

        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | JAXBException ex) {
            ex.printStackTrace();
        }
    }
}