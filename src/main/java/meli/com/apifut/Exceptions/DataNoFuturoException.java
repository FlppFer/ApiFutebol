package meli.com.apifut.Exceptions;

public class DataNoFuturoException extends RuntimeException{
    public DataNoFuturoException() {
        super("A data é inválida!");
    }
    public DataNoFuturoException(String message) {
        super(message);
    }
}
