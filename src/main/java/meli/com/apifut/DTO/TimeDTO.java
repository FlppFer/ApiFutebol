package meli.com.apifut.DTO;

import meli.com.apifut.Model.Time;

public class TimeDTO {
    private String nome;
    private String estado;
    private String criacao;
    private boolean status;

    public TimeDTO(){

    }
    public TimeDTO(Time time){
        this.nome = time.getNome();
        this.estado = time.getEstado();
        this.criacao = time.getCriacao();
        this.status = time.getStatus();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}



