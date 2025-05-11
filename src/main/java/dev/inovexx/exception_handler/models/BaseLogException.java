package dev.inovexx.exception_handler.models;

import org.springframework.http.HttpStatus;

public class BaseLogException extends BaseException {

    private final String log;

    public BaseLogException(String message, HttpStatus status, String log) {
        super(message, status);
        this.log = log;
    }

    public BaseLogException(String message, String log) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
        this.log = log;
    }

    public BaseLogException(String message, HttpStatus status) {
        super(message, status);
        this.log = message;
    }

    public BaseLogException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
        this.log = message;
    }

    public String getLog() {
        return log;
    }
}
