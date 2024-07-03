package meli.com.apifut.Model;
import meli.com.apifut.DTO.PartidaDTO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Partida {
    private Time timeCasa;
    private Time timeVisitante;
    private String resultado;
    private String estadio;
    private LocalDateTime dataHora;

    private static final Map<Long, PartidaDTO> listaPartidas = new ConcurrentHashMap<>();

    public Partida() {

    }

    public Partida(Time timeCasa, Time timeVisitante, String resultado, String estadio, LocalDateTime dataHora) {
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

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
