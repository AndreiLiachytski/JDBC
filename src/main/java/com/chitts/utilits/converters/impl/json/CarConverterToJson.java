package com.chitts.utilits.converters.impl.json;

import com.chitts.model.Model;
import com.chitts.model.impl.Car;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CarConverterToJson<T extends Model> extends GenericConverterToJson<T> {

    @Override
    public String convert(final List<T> list, final Class<T> cl) throws JSONException {
        final JSONArray array = new JSONArray(list.size());
        for (final Model model : list) {
            final Car car = (Car) model;
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", car.getId());
            jsonObject.put("name", car.getName());
            jsonObject.put("power", car.getPower());
            array.put(jsonObject);
        }
        return array.toString();
    }

    @Override
    public String convert(final T t, final Class<T> cl) {
        final Car car = (Car) t;
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", car.getId());
        jsonObject.put("name", car.getName());
        jsonObject.put("power", car.getPower());

        return jsonObject.toString();
    }
}