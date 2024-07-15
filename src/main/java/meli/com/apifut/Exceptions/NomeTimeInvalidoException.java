package meli.com.apifut.Exceptions;

public class nomeTimeInvalido extends RuntimeException{
    public nomeTimeInvalido() {
        super("Nomes com menos que 2 letras são inválidos!");
    }
    public nomeTimeInvalido(String message) {
        super(message);
    }
}

