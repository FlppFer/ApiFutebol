package meli.com.apifut.Service;

import meli.com.apifut.Model.Partida;
import meli.com.apifut.DTO.PartidaDTO;
import meli.com.apifut.Repository.EstadioRepository;
import meli.com.apifut.Repository.PartidaRepository;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private EstadioRepository estadioRepository;


    public void criarNovaPartida(PartidaDTO partidaDTO) {
        if (partidaDTO.getTimeCasa() != null || partidaDTO.getTimeVisitante() != null || partidaDTO.getResultado() != null || partidaDTO.getEstadio() != null || partidaDTO.getDataPartida() != null) {
            Partida partida = converterEntidade(partidaDTO);
            partidaRepository.save(partida);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void editarPartida(long id, PartidaDTO PartidaDTO) {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);

        if (optionalPartida.isPresent() && PartidaDTO.getTimeCasa() != null && PartidaDTO.getTimeVisitante() != null && PartidaDTO.getResultado() != null && PartidaDTO.getEstadio() != null && PartidaDTO.getDataPartida() != null) {
            Partida partida = optionalPartida.get();

            partida.setTimeCasa(PartidaDTO.getTimeCasa());
            partida.setTimeVisitante(PartidaDTO.getTimeVisitante());
            partida.setResultado(PartidaDTO.getResultado());
            partida.setEstadio(PartidaDTO.getEstadio());
            partida.setDataPartida(PartidaDTO.getDataPartida());

            partidaRepository.save(partida);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void removerPartidaPorID(long id) {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);
        if (optionalPartida.isPresent()) {
            partidaRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Partida buscarPartidaPorId(Long id) {
        if (id != null && partidaRepository.existsById(id)) {
            return partidaRepository.findById(id).get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Page<Partida> listarTodasPartidas(Long timeId, Long estadioId, Pageable pageable) {
        if(timeId == null && estadioId == null) {
            return partidaRepository.findAll(pageable);
        } else if (timeId != null && timeRepository.existsById(timeId)) {
            return partidaRepository.findPartidaByTimeId(timeId, pageable);
        } else if (estadioId != null && estadioRepository.existsById(estadioId)) {
            return partidaRepository.findPartidaByEstadioId(estadioId, pageable);
        } else {
            return Page.empty();
        }
    }

    private Partida converterEntidade(PartidaDTO partidaDTO) {
        Partida partida = new Partida();

        partida.setTimeCasa(partidaDTO.getTimeCasa());
        partida.setTimeVisitante(partidaDTO.getTimeVisitante());
        partida.setResultado(partidaDTO.getResultado());
        partida.setEstadio(partidaDTO.getEstadio());
        partida.setDataPartida(partidaDTO.getDataPartida());

        return partida;
    }

    private PartidaDTO converterDTO(Partida partida) {
        PartidaDTO partidaDTO = new PartidaDTO();

        partidaDTO.setTimeCasa(partida.getTimeCasa());
        partidaDTO.setTimeVisitante(partida.getTimeVisitante());
        partidaDTO.setResultado(partida.getResultado());
        partidaDTO.setEstadio(partida.getEstadio());
        partidaDTO.setDataPartida(partida.getDataPartida());

        return partidaDTO;
    }
}
