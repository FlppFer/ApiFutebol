package meli.com.apifut.Exceptions;

public class TimeNaoEncontrado extends RuntimeException{
    public TimeNaoEncontrado() {
        super("Time não encontrado!");
    }
    public TimeNaoEncontrado(String message) {
        super(message);
    }
}
