package meli.com.apifut.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeDTO {
    private long id;
    private String nome;
    private String siglaEstado;
    private LocalDateTime dataCriacao;
    private Boolean status;

    public TimeDTO() {

    }
    public TimeDTO(String nome, String siglaEstado, LocalDateTime dataCriacao, Boolean status) {
        this.nome = nome;
        this.siglaEstado = siglaEstado;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}



