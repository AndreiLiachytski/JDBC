package com.example.servlets;

import com.example.config.DataBaseConnection;
import com.example.dao.Dao;
import com.example.factory.impl.DaoFactory;
import com.example.model.impl.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter pw = response.getWriter();
        DataBaseConnection connection = null;
        try {
            connection = DataBaseConnection.getInstance();
        } catch (final SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            assert connection != null;
            final DaoFactory daoFactory = new DaoFactory(connection.getConnection());
            final Dao userDao = daoFactory.createDao(User.class);

            userDao.getAll().forEach(pw::println);
            userDao.sortingByName().forEach(pw::println);

        } catch (final SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}

