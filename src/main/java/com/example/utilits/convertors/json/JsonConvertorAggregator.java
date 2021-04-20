package com.example.utilits.convertors.json;

import com.example.model.Model;
import com.example.model.impl.Car;
import com.example.model.impl.User;
import com.example.utilits.convertors.json.impl.CarConvertorToJson;
import com.example.utilits.convertors.json.impl.UserConvertorToJson;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConvertorAggregator {

    private final static Map<Class<?>, Convertor<JSONArray>> convertors = new HashMap<>();

    static {
        convertors.put(Car.class, new CarConvertorToJson());
        convertors.put(User.class, new UserConvertorToJson());
    }

    public static JSONArray getJsonArray(final List<? extends Model> list, final Class<? extends Model> cl) {
        final Convertor<JSONArray> convertor = convertors.get(cl);

        return convertor.convert(list);
    }
}