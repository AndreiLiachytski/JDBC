package com.example.servlets;

import com.example.configs.DataBaseConnection;
import com.example.dao.Dao;
import com.example.dao.daoImpl.GenericDao;
import com.example.model.impl.User;
import com.example.utilits.convertors.XML.XmlConvertor;
import com.example.utilits.mappers.UserMapper;

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
            final XmlConvertor<User> userConvertor = new XmlConvertor<>();
            writer.println(userConvertor.listObjectsAsXML(users, User.class));
            writer.close();

        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException | JAXBException ex) {
            ex.printStackTrace();
        }
    }


}