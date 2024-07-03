package meli.com.apifut.DTO;

public class EstadioDTO {
    private String nomeDoEstadio;

    public EstadioDTO(String nomeDoEstadio) {
        this.nomeDoEstadio = nomeDoEstadio;
    }

    public EstadioDTO(){

    }

    public String getNomeDoEstadio() {
        return nomeDoEstadio;
    }

    public void setNomeDoEstadio(String nomeDoEstadio) {
        this.nomeDoEstadio = nomeDoEstadio;
    }

}
