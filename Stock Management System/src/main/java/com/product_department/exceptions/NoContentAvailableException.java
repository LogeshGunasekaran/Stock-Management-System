package com.product_department.exceptions;


public class NoContentAvailableException extends RuntimeException{

    private String field;

    public NoContentAvailableException( String field ,String message) {
        super(message);
        this.field = field;
    }
    public NoContentAvailableException(){}

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
