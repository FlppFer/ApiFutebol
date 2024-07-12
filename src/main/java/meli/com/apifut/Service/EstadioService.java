package meli.com.apifut.Service;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Repository.EstadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    public void criarEstadio(EstadioDTO estadioDTO) {
        if (estadioDTO.getNome()!=null){
            Estadio estadio = converterEntidade(estadioDTO);
            estadioRepository.save(estadio);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void editarEstadio(long id, EstadioDTO estadioDTO) {
        Optional<Estadio> optionalEstadio = estadioRepository.findById(id);
        if (optionalEstadio.isPresent()) {
            Estadio estadio = optionalEstadio.get();
            estadio.setNome(estadioDTO.getNome());
            estadioRepository.save(estadio);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Estadio buscarEstadioPorID(long idEstadio) {
        Optional<Estadio> optionalEstadio = estadioRepository.findById(idEstadio);
        if (optionalEstadio.isPresent()) {
            return optionalEstadio.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Page<Estadio> listarEstadios(Pageable pageable) {
        return estadioRepository.findAll(pageable);
    }


    private Estadio converterEntidade(EstadioDTO estadioDTO) {
        Estadio estadio = new Estadio();
        estadio.setNome(estadioDTO.getNome());

        return estadio;
    }

    private EstadioDTO converterDTO(Estadio estadio) {
        EstadioDTO estadioDTO = new EstadioDTO();
        estadioDTO.setNome(estadio.getNome());

        return estadioDTO;
    }

}
