package com.example.utilits.convertors.json.impl;

import com.example.model.Model;
import com.example.model.impl.Car;
import com.example.utilits.convertors.json.Convertor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CarConvertorToJson implements Convertor<JSONArray> {

    @Override
    public JSONArray convert(final List<? extends Model> list) {
        final JSONArray array = new JSONArray(list.size());

        for (final Model model : list) {
            final Car car = (Car) model;
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", car.getId());
            jsonObject.put("name", car.getName());
            jsonObject.put("power", car.getPower());
            array.put(jsonObject);
        }
        return array;
    }

}