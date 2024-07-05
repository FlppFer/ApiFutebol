package meli.com.apifut.DTO;

import meli.com.apifut.Model.Time;

public class TimeDTO {
    private String nomeDoTime;
    private String siglaEstado;
    private String dataCriacao;
    private boolean status;

    public TimeDTO() {

    }

    public TimeDTO(Time time) {
        this.nomeDoTime = time.getNomeDoTime();
        this.siglaEstado = time.getSiglaEstado();
        this.dataCriacao = time.getDataCriacao();
        this.status = time.getStatus();
    }


    public String getNomeDoTime() {
        return nomeDoTime;
    }

    public void setNomeDoTime(String nomeDoTime) {
        this.nomeDoTime = nomeDoTime;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}



