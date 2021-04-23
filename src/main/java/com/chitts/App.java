package com.chitts;

import com.chitts.configs.DataBaseConnection;
import com.chitts.dao.Dao;
import com.chitts.dao.impl.GenericDao;
import com.chitts.exceptions.ClassConverterException;
import com.chitts.exceptions.TypeConverterException;
import com.chitts.model.impl.Car;
import com.chitts.model.impl.User;
import com.chitts.utilits.converters.ConverterAggregator;
import com.chitts.utilits.mappers.impl.CarMapper;
import com.chitts.utilits.mappers.impl.UserMapper;

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


            final ConverterAggregator<Car> carConverter = new ConverterAggregator<>();
            final ConverterAggregator<User> userConverter = new ConverterAggregator<>();

            System.out.println(userConverter.convertTo("Xml", users.get(0), User.class));
            System.out.println(userConverter.convertTo("json", users.get(0), User.class));
            System.out.println(carConverter.convertTo("XML", cars.get(0), Car.class));
            System.out.println(carConverter.convertTo("json", cars.get(0), Car.class));

            System.out.println(userConverter.convertTo("Xml", users, User.class));
            System.out.println(userConverter.convertTo("json", users, User.class));
            System.out.println(carConverter.convertTo("XML", cars, Car.class));
            System.out.println(carConverter.convertTo("json", cars, Car.class));


        } catch (final IOException | SQLException | JAXBException | TypeConverterException | ClassConverterException ex) {
            ex.printStackTrace();
        }
    }
}