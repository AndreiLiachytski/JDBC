package com.chitts.converters.json.impl;

import com.chitts.converters.json.Converter;
import com.chitts.model.impl.Car;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CarConverterToJson implements Converter<Car, String> {

    private JSONObject getJsonObject(final Car car) {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", car.getId());
        jsonObject.put("name", car.getName());
        jsonObject.put("power", car.getPower());

        return jsonObject;
    }

    @Override
    public String convert(final Car car) {
        return getJsonObject(car).toString();
    }

    @Override
    public String convert(final List<Car> list) {
        final JSONArray array = new JSONArray(list.size());
        for (final Car car : list) {
            array.put(getJsonObject(car));
        }
        return array.toString();
    }
}