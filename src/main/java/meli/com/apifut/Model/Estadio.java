package meli.com.apifut.Model;

import jakarta.persistence.*;
import meli.com.apifut.DTO.EstadioDTO;

@Entity
@Table(name = "estadio")
public class Estadio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Estadio() {

    }

    public Estadio(EstadioDTO estadioDTO) {
        this.nome = estadioDTO.getNome();
    }

    public Estadio(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Estadio(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
