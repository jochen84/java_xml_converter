package org.example.exception;

public class ToManyValuesException extends RuntimeException{
    public ToManyValuesException(String errorMessage){
        super(errorMessage);
    }
}
