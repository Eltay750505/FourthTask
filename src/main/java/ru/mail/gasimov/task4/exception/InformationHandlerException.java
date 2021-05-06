package ru.mail.gasimov.task4.exception;

public class InformationHandlerException extends Exception {
    public InformationHandlerException() {
    }

    public InformationHandlerException(String message) {
        super(message);
    }

    public InformationHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InformationHandlerException(Throwable cause) {
        super(cause);
    }

    public InformationHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
