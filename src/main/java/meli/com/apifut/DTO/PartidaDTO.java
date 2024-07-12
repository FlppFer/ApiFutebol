package meli.com.apifut.DTO;


import jakarta.persistence.ManyToOne;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Model.Time;
import java.time.LocalDateTime;

public class PartidaDTO {

    private long id;

    @ManyToOne
    private Time timeCasa;
    @ManyToOne
    private Time timeVisitante;
    @ManyToOne
    private Estadio estadio;
    private String resultado;
    private LocalDateTime dataHora;

    public PartidaDTO() {

    }

    public PartidaDTO(Time timeCasa, Time timeVisitante, String resultado, Estadio estadio, LocalDateTime dataHora) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.resultado = resultado;
        this.estadio = estadio;
        this.dataHora = dataHora;
    }


    public Time getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(Time timeCasa) {
        this.timeCasa = timeCasa;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
