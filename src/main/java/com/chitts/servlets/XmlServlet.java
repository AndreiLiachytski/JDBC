package com.chitts.servlets;

import com.chitts.configs.DataBaseConnection;
import com.chitts.dao.Dao;
import com.chitts.dao.impl.GenericDao;
import com.chitts.exceptions.AppException;
import com.chitts.model.impl.User;
import com.chitts.converters.ConverterAggregator;
import com.chitts.mappers.impl.UserMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getXml")
public class XmlServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        try (final Connection connection = new DataBaseConnection().getConnection()) {
            final PrintWriter writer = response.getWriter();
            final Dao<User> userDao = new GenericDao<>(connection, "users", UserMapper.getUserMapper());
            final List<User> users = userDao.getAll();
            final ConverterAggregator<User> userConverter = new ConverterAggregator<>();
            writer.println(userConverter.convertTo("Xml", users.get(0), User.class));
            writer.close();

        } catch (final IOException | SQLException | JAXBException | AppException ex) {
            ex.printStackTrace();
        }
    }
}