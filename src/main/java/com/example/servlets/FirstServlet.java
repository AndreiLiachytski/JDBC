package com.example.servlets;

import com.example.config.DataBaseConnection;
import com.example.dao.Dao;
import com.example.factory.impl.DaoFactory;
import com.example.model.impl.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final PrintWriter pw = response.getWriter();
        final DataBaseConnection connection;

        try {
            connection = DataBaseConnection.getInstance();
            assert connection != null;
            final DaoFactory daoFactory = new DaoFactory(connection.getConnection());
            final Dao userDao = daoFactory.createDao(User.class);
            final ObjectMapper objectMapper = new ObjectMapper();
            userDao.getAll().forEach(value -> {
                try {
                    pw.println(objectMapper.writeValueAsString(value));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (final SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}

