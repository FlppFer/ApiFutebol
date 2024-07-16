package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class EstadioNaoEncontradoException extends RuntimeException{
    private final HttpStatus status;

    public EstadioNaoEncontradoException(HttpStatus status) {
        super("Estadio não encontrado!");
        this.status = status;
    }
    public EstadioNaoEncontradoException(String message, HttpStatus status  ) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
