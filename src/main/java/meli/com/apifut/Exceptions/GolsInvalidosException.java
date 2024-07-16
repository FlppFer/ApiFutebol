package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class GolsInvalidosException extends RuntimeException{
    private final HttpStatus status;

    public GolsInvalidosException(HttpStatus status) {
        super("Número de gols inválido!");
        this.status = status;
    }
    public GolsInvalidosException(String message, HttpStatus status  ) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
