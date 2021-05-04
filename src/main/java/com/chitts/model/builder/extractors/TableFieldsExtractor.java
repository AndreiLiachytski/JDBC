package com.chitts.model.builder.extractors;

import com.chitts.configs.DataBaseConnection;
import com.chitts.exceptions.DataBaseConnectionException;
import com.chitts.exceptions.TableFieldsExtractorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TableFieldsExtractor {

    private final Class<?> cl;
    private final Connection connection;
    private final String sql;

    public TableFieldsExtractor(final Class<?> cl) throws DataBaseConnectionException {
        this.cl = cl;
        this.connection = new DataBaseConnection().getConnection();
        this.sql = "SELECT column_name,data_type FROM information_schema.columns WHERE table_name = '" + getTableName() + "'";
    }

    public Map<String, String> getTableColumns() throws TableFieldsExtractorException {
        final Map<String, String> tableColumns = new HashMap<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSetGetDbColumns = preparedStatement.executeQuery();
            while (resultSetGetDbColumns.next()) {
                tableColumns.put(resultSetGetDbColumns.getString("column_name"), resultSetGetDbColumns.getString("data_type"));
            }
        } catch (final SQLException throwable) {
            throw new TableFieldsExtractorException("Check SQL query in TableFieldsExtractor.class.", throwable);
        }
        return tableColumns;
    }

    private String getTableName() {
        return cl.getSimpleName().concat("s").toLowerCase();
    }
}