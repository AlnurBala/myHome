package com.startup.myhome.exception;

public class MonthlyLimitExceededException extends RuntimeException {

    public MonthlyLimitExceededException(String message) {
        super(message);
    }
}