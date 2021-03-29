package org.example.exception;

public class NoFileToConvertException extends RuntimeException{
    public NoFileToConvertException(String errorMessage) {
        super(errorMessage);
    }
}
