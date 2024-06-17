package com.javarush.alimova.exception;

public class ConnectionDBException extends RuntimeException{

    public ConnectionDBException() {
        super();
    }

    public ConnectionDBException(String message) {
        super(message);
    }

    public ConnectionDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionDBException(Throwable cause) {
        super(cause);
    }
}
