package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class TimeDuplicadoException extends RuntimeException{
    private final HttpStatus status;

    public TimeDuplicadoException(HttpStatus status) {
        super("Não é possível passar dois times com o mesmo nome!");
        this.status = status;
    }
    public TimeDuplicadoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
