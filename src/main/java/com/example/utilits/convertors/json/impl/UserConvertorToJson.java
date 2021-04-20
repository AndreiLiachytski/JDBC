package com.example.utilits.convertors.json.impl;

import com.example.model.Model;
import com.example.model.impl.User;
import com.example.utilits.convertors.json.Convertor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserConvertorToJson implements Convertor<JSONArray> {

    @Override
    public JSONArray convert(final List<? extends Model> list) {
        final JSONArray array = new JSONArray(list.size());

        for (final Model model : list) {
            final User user = (User) model;
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            jsonObject.put("email", user.getEmail());
            array.put(jsonObject);
        }
        return array;
    }
}