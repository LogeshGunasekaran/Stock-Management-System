package com.product_department.exceptions;

public class CouldNotAddException extends RuntimeException{

    public CouldNotAddException(String message) {
        super(message);
    }

    public CouldNotAddException(String message, Throwable cause) {
        super(message, cause);
    }
}
