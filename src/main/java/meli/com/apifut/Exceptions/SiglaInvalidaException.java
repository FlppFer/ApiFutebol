package meli.com.apifut.Exceptions;

public class SiglaInvalidaException extends RuntimeException{
    public SiglaInvalidaException() {
        super("A sigla do estado é inválida!");
    }
    public SiglaInvalidaException(String message) {
        super(message);
    }
}
