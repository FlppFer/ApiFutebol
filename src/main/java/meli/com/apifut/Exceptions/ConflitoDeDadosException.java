package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class ConflitoDeDadosException extends RuntimeException{
    private final HttpStatus status;

    public ConflitoDeDadosException(HttpStatus status) {
        super("A data de criação não pode ser posterior à data de uma partida em que o time jogou");
        this.status = status;
    }
    public ConflitoDeDadosException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
