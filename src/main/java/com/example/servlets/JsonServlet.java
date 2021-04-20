package com.example.servlets;

import com.example.configs.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.daoImpl.GenericDao;
import com.example.model.impl.User;
import com.example.utilits.convertors.json.JsonConvertorAggregator;
import com.example.utilits.mappers.UserMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getJson")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {

        try (final Connection connection = new DataBaseConnection().getConnection()) {
            final PrintWriter writer = response.getWriter();
            final Dao<User> userDao = new GenericDao<>(connection, "users", UserMapper.getUserMapper());
            final List<User> users = userDao.getAll();
            writer.println(JsonConvertorAggregator.getJsonArray(users, User.class));
            writer.close();

        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}