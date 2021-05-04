package com.chitts.converters.json.impl;

import com.chitts.converters.json.Converter;
import com.chitts.model.impl.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserConverterToJson implements Converter<User, String> {

    private JSONObject getJsonObject(final User user) {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());

        return jsonObject;
    }

    @Override
    public String convert(final User user) {
        return getJsonObject(user).toString();
    }

    @Override
    public String convert(final List<User> list) {
        final JSONArray array = new JSONArray(list.size());
        for (final User user : list) {
            array.put(getJsonObject(user));
        }
        return array.toString();
    }
}