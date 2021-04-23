package com.chitts.utilits.converters.impl.json;

import com.chitts.model.Model;
import com.chitts.model.impl.User;
import com.chitts.utilits.converters.Converter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserConverterToJson<T extends Model> implements Converter<T, String> {

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JSONException {
        final JSONArray array = new JSONArray(list.size());

        for (final Model model : list) {
            final User user = (User) model;
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            jsonObject.put("email", user.getEmail());
            array.put(jsonObject);
        }
        return array.toString();
    }

    @Override
    public String convert(final T t, final Class<T> cl) {
        final User user = (User) t;
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());

        return jsonObject.toString();
    }
}