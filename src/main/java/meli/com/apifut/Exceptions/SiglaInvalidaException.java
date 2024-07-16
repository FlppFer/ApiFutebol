package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class SiglaInvalidaException extends RuntimeException{
    private final HttpStatus status;

    public SiglaInvalidaException(HttpStatus status) {
        super("A sigla do estado é inválida!");
        this.status = status;
    }
    public SiglaInvalidaException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
