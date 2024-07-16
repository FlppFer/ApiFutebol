package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class TimeInativoException extends RuntimeException{
    private final HttpStatus status;

    public TimeInativoException(HttpStatus status) {
        super("Um dos times passados está inativo");
        this.status = status;
    }
    public TimeInativoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
