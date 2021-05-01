package com.chitts.exceptions.converter;

import com.chitts.exceptions.AppException;

public class ConverterException extends AppException {

    public ConverterException() {
    }

    public ConverterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
