package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class EntidadeNaoEncontradaException extends RuntimeException{
    private final HttpStatus status;

    public EntidadeNaoEncontradaException(HttpStatus status) {
        super("O elemento com o ID digitado não foi encontrado!");
        this.status = status;
    }
    public EntidadeNaoEncontradaException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
