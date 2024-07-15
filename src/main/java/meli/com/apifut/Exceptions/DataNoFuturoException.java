package meli.com.apifut.Exceptions;

public class DataInvalidaException extends RuntimeException{
    public DataInvalidaException() {
        super("A data é inválida!");
    }
    public DataInvalidaException(String message) {
        super(message);
    }
}
