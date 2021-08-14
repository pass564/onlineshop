package org.example.common.exception;

public class SysuserNotExistException extends Exception{
    public SysuserNotExistException() {
    }

    public SysuserNotExistException(String message) {
        super(message);
    }

    public SysuserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysuserNotExistException(Throwable cause) {
        super(cause);
    }
}
