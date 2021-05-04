package com.chitts;

import com.chitts.converters.ConverterAggregator;
import com.chitts.dao.impl.GenericDao;
import com.chitts.exceptions.AppException;
import com.chitts.model.impl.Car;
import com.chitts.model.impl.User;

import javax.xml.bind.JAXBException;
import java.util.List;

public class App {
    public static void main(final String[] args)  {

        try {
            final GenericDao<User> userDao = new GenericDao<>(User.class);
            final GenericDao<Car> carDao = new GenericDao<>(Car.class);
            final List<User> userList = userDao.getAll();
            final List<Car> carList = carDao.getAll();

            final ConverterAggregator<User> userConverterAggregator = new ConverterAggregator<>();
            final ConverterAggregator<Car> carConverterAggregator = new ConverterAggregator<>();
            System.out.println(userConverterAggregator.convertTo("json", userList, User.class));
            System.out.println(carConverterAggregator.convertTo("json", carList, Car.class));
            System.out.println(userDao.getAllSortedByName());

        } catch (final AppException | JAXBException e) {
            e.printStackTrace();
        }
    }
}