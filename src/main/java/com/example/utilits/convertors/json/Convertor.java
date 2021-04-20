package com.example.utilits.convertors.json;

import com.example.model.Model;

import java.util.List;

public interface Convertor<T> {

    T convert(List<? extends Model> list);

}