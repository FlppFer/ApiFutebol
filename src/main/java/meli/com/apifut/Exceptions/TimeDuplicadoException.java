package meli.com.apifut.Exceptions;

public class TimeDuplicadoException extends RuntimeException{
    public TimeDuplicadoException() {
        super("Um time de mesmo nome já está cadastrado neste estado!");
    }
    public TimeDuplicadoException(String message) {
        super(message);
    }
}
