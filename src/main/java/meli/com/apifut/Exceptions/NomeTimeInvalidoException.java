package meli.com.apifut.Exceptions;

public class NomeTimeInvalidoException extends RuntimeException{
    public NomeTimeInvalidoException() {
        super("Nomes com menos que 2 letras são inválidos!");
    }
    public NomeTimeInvalidoException(String message) {
        super(message);
    }
}

