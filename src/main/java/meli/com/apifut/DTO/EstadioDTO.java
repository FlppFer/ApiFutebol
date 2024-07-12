package meli.com.apifut.DTO;

public class EstadioDTO {
    private String nome;

    public EstadioDTO(){

    }

    public EstadioDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
