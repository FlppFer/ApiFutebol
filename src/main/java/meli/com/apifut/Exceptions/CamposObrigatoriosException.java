package meli.com.apifut.Exceptions;

import org.springframework.http.HttpStatus;

public class CamposObrigatoriosException extends RuntimeException{
    private final HttpStatus status;

    public CamposObrigatoriosException(HttpStatus status) {
        super("Preencha todos os campos obrigatórios!");
        this.status = status;
    }
    public CamposObrigatoriosException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
