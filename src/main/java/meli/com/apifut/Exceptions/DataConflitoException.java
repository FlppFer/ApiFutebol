package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class DataConflitoException extends RuntimeException{
    private final HttpStatus status;

    public DataConflitoException(HttpStatus status) {
        super("A data de criação não pode ser posterior à data de uma partida em que o time jogou");
        this.status = status;
    }
    public DataConflitoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
