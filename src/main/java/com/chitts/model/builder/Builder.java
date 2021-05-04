package com.chitts.model.builder;

import com.chitts.exceptions.GenericBuilderException;
import com.chitts.exceptions.TableFieldsExtractorException;
import com.chitts.model.Model;

import java.sql.ResultSet;

public interface Builder<T extends Model> {

    void build(T model, ResultSet resultSet) throws GenericBuilderException, TableFieldsExtractorException;
}
