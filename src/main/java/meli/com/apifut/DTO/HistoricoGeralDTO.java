package meli.com.apifut.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import meli.com.apifut.Model.Time;

public class HistoricoGeralDTO {
    private Long vitorias;
    private Long empates;
    private Long derrotas;
    private Long golsFeitos;
    private Long golsSofridos;

    public HistoricoGeralDTO() {

    }

    public HistoricoGeralDTO(Long vitorias, Long empates, Long derrotas, Long golsFeitos, Long golsSofridos) {
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.golsFeitos = golsFeitos;
        this.golsSofridos = golsSofridos;
    }


    public Long getVitorias() {
        return vitorias;
    }

    public void setVitorias(Long vitorias) {
        this.vitorias = vitorias;
    }

    public Long getEmpates() {
        return empates;
    }

    public void setEmpates(Long empates) {
        this.empates = empates;
    }

    public Long getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Long derrotas) {
        this.derrotas = derrotas;
    }

    public Long getGolsFeitos() {
        return golsFeitos;
    }

    public void setGolsFeitos(Long golsFeitos) {
        this.golsFeitos = golsFeitos;
    }

    public Long getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(Long golsSofridos) {
        this.golsSofridos = golsSofridos;
    }
}
