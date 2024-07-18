package meli.com.apifut.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Time timeCasa;
    @ManyToOne
    private Time timeVisitante;
    @ManyToOne
    private Estadio estadio;
    private Long golsTimeCasa;
    private Long golsTimeVisitante;
    private String resultado;
    private LocalDateTime dataHoraPartida;

    public Partida() {

    }

    public Partida(Time timeCasa, Time timeVisitante, Estadio estadio, Long golsTimeCasa, Long golsTimeVisitante, String resultado, LocalDateTime dataHoraPartida) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.estadio = estadio;
        this.golsTimeCasa = golsTimeCasa;
        this.golsTimeVisitante = golsTimeVisitante;
        this.resultado = resultado;
        this.dataHoraPartida = dataHoraPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getGolsTimeVisitante() {
        return golsTimeVisitante;
    }

    public void setGolsTimeVisitante(Long golsTimeVisitante) {
        this.golsTimeVisitante = golsTimeVisitante;
    }

    public Long getGolsTimeCasa() {
        return golsTimeCasa;
    }

    public void setGolsTimeCasa(Long golsTimeCasa) {
        this.golsTimeCasa = golsTimeCasa;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

}
