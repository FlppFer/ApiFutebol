package meli.com.apifut.DTO;


import jakarta.persistence.ManyToOne;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Model.Time;

import java.time.LocalDate;

public class PartidaDTO {

    private long id;

    @ManyToOne
    private Time timeCasa;
    @ManyToOne
    private Time timeVisitante;
    @ManyToOne
    private Estadio estadio;
    private String resultado;
    private LocalDate dataPartida;

    public PartidaDTO() {

    }

    public PartidaDTO(long id, Time timeCasa, Time timeVisitante, Estadio estadio, String resultado, LocalDate dataPartida) {
        this.id = id;
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.estadio = estadio;
        this.resultado = resultado;
        this.dataPartida = dataPartida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }
}