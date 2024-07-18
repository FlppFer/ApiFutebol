package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class DadosInvalidosException extends RuntimeException{
    private final HttpStatus status;

    public DadosInvalidosException(HttpStatus status) {
        super("A sigla do estado é inválida!");
        this.status = status;
    }
    public DadosInvalidosException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
