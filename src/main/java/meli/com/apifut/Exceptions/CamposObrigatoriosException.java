package meli.com.apifut.Exceptions;

public class CamposObrigatoriosException extends RuntimeException{
    public CamposObrigatoriosException() {
        super("Preencha todos os campos obrigatórios!");
    }
    public CamposObrigatoriosException(String message) {
        super(message);
    }
}
