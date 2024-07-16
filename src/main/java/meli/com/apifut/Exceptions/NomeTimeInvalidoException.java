package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class NomeTimeInvalidoException extends RuntimeException{
    private final HttpStatus status;

    public NomeTimeInvalidoException(HttpStatus status) {
        super("Nomes com menos que 2 letras são inválidos!");
        this.status = status;
    }
    public NomeTimeInvalidoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}

