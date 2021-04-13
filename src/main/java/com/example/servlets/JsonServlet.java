package com.example.servlets;

import com.example.configs.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.daoImpl.GenericDao;
import com.example.model.impl.Car;
import com.example.model.impl.User;
import com.example.servlets.util.ServletUtil;
import com.example.utilits.convertors.JsonConvertor;
import com.example.utilits.mappers.CarMapper;
import com.example.utilits.mappers.UserMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getJson")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {

        try {
            final Dao<User> userDao = new GenericDao<>(DataBaseConnection
                    .getInstance()
                    .getConnection(), "users", UserMapper.getUserMapper());
            final Dao<Car> carDao = new GenericDao<>(DataBaseConnection
                    .getInstance()
                    .getConnection(), "cars", CarMapper.getCarMapper());

            final List<User> users = userDao.getAll();
            final List<Car> cars = carDao.getAll();

            final JsonConvertor<User> userToJsonConvertor = new JsonConvertor<>();
            final JsonConvertor<Car> CarToJsonConvertor = new JsonConvertor<>();

            ServletUtil.getWriter(response).println(userToJsonConvertor.getJsonArray(users, User.class));
            ServletUtil.getWriter(response).println(CarToJsonConvertor.getJsonArray(cars, Car.class));

        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}