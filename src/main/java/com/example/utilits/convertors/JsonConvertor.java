package com.example.utilits.convertors;

import com.example.model.Model;
import com.example.model.impl.Car;
import com.example.model.impl.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonConvertor<T extends Model> {

    public JSONArray getJsonArray(final List<T> list, final Class<T> cl) throws JsonProcessingException {
        final JSONArray array = new JSONArray(list.size());

        if (cl.equals(Car.class)) {
            for (final T model : list) {
                final Car car = (Car) model;
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", car.getId());
                jsonObject.put("name", car.getName());
                jsonObject.put("power", car.getPower());
                array.put(jsonObject);
            }
        }
        if (cl.equals(User.class)) {
            for (final T model : list) {
                final User user = (User) model;
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("name", user.getName());
                jsonObject.put("email", user.getEmail());
                array.put(jsonObject);
            }
        }
        return array;
    }
}