package com.example.dice.assignment.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
