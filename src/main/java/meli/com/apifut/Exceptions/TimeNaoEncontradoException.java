package meli.com.apifut.Exceptions;

public class TimeNaoEncontradoException extends RuntimeException{
    public TimeNaoEncontradoException() {
        super("Um time com o ID digitado não foi encontrado!");
    }
    public TimeNaoEncontradoException(String message) {
        super(message);
    }
}
