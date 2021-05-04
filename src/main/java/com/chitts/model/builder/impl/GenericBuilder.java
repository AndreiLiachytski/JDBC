package com.chitts.model.builder.impl;

import com.chitts.exceptions.DataBaseConnectionException;
import com.chitts.exceptions.GenericBuilderException;
import com.chitts.exceptions.TableFieldsExtractorException;
import com.chitts.model.Model;
import com.chitts.model.builder.Builder;
import com.chitts.model.builder.extractors.ClassFieldsExtractor;
import com.chitts.model.builder.extractors.TableFieldsExtractor;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class GenericBuilder<T extends Model> implements Builder<T> {

    private final Class<T> cl;
    private final TableFieldsExtractor tableFieldsExtractor;
    private final ClassFieldsExtractor<T> classFieldsExtractor;

    public GenericBuilder(final Class<T> cl) throws DataBaseConnectionException {
        this.cl = cl;
        this.tableFieldsExtractor = new TableFieldsExtractor(cl);
        this.classFieldsExtractor = new ClassFieldsExtractor<>(cl);
    }

    @Override
    public void build(final T model, final ResultSet resultSet) throws GenericBuilderException, TableFieldsExtractorException {

        try {
            final Map<String, String> names = tableFieldsExtractor.getTableColumns();
            final Field[] fields = classFieldsExtractor.getFields();

            for (final Field field : fields) {
                field.setAccessible(true);
                if (names.get(field.getName()).equals("integer")) {
                    field.set(model, resultSet.getInt(field.getName()));
                } else {
                    field.set(model, resultSet.getString(field.getName()));
                }
                field.setAccessible(false);
            }
        } catch (final SQLException throwable) {
            throw new GenericBuilderException("ResultSet Exception in GenericBuilder.class. Check table name.", throwable);
        } catch (final IllegalAccessException throwable) {
            throw new GenericBuilderException("You haven't got access to fields of " + cl + ".", throwable);
        } catch (final NullPointerException throwable) {
            throw new GenericBuilderException("Fields of entity " + cl + " weren't implemented in data base.", throwable);
        }
    }
}