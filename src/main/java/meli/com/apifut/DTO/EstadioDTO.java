package meli.com.apifut.DTO;

public class EstadioDTO {

    private Long Id;
    private String nome;

    public EstadioDTO(){

    }

    public EstadioDTO(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
