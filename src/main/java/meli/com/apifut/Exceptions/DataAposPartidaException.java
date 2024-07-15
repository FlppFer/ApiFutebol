package meli.com.apifut.Exceptions;

public class DataAposPartidaException extends RuntimeException{
    public DataAposPartidaException() {
        super("A data de criação não pode ser posterior à data de uma partida em que o time jogou");
    }
    public DataAposPartidaException(String message) {
        super(message);
    }
}
