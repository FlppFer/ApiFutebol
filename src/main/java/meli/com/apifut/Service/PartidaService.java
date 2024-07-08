package meli.com.apifut.Service;

import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Model.Partida;
import meli.com.apifut.DTO.PartidaDTO;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Repository.PartidaRepository;
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


    public void criarNovaPartida(PartidaDTO partidaDTO) {
        if (partidaDTO.getTimeCasa() == null || partidaDTO.getTimeVisitante() == null || partidaDTO.getResultado() == null || partidaDTO.getEstadio() == null || partidaDTO.getDataHora() == null) {
            Partida partida = converterEntidade(partidaDTO);
            partidaRepository.save(partida);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void editarPartida(long id, PartidaDTO PartidaDTO) {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);

        if (optionalPartida.isPresent() && PartidaDTO.getTimeCasa() != null && PartidaDTO.getTimeVisitante() != null && PartidaDTO.getResultado() != null && PartidaDTO.getEstadio() != null && PartidaDTO.getDataHora() != null) {
            Partida partida = optionalPartida.get();

            partida.setTimeCasa(PartidaDTO.getTimeCasa());
            partida.setTimeVisitante(PartidaDTO.getTimeVisitante());
            partida.setResultado(PartidaDTO.getResultado());
            partida.setEstadio(PartidaDTO.getEstadio());
            partida.setDataHora(PartidaDTO.getDataHora());

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

    public Partida buscarPartidaPorId(long id) {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);
        if (optionalPartida.isPresent()) {
            return optionalPartida.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Page<Partida> listarPartidas(Time time, Estadio estadio, Pageable pageable) {
        if (time == null && estadio == null) {
            return partidaRepository.findAll(pageable);
        } else {
            return partidaRepository.findByTimeCasaOrTimeVisitanteOrEstadio(time, time, estadio, pageable);
        }
    }

    private Partida converterEntidade(PartidaDTO partidaDTO) {
        Partida partida = new Partida();

        partida.setTimeCasa(partidaDTO.getTimeCasa());
        partida.setTimeVisitante(partidaDTO.getTimeVisitante());
        partida.setResultado(partidaDTO.getResultado());
        partida.setEstadio(partidaDTO.getEstadio());
        partida.setDataHora(partidaDTO.getDataHora());

        return partida;
    }

    private PartidaDTO converterDTO(Partida partida) {
        PartidaDTO partidaDTO = new PartidaDTO();

        partidaDTO.setTimeCasa(partida.getTimeCasa());
        partidaDTO.setTimeVisitante(partida.getTimeVisitante());
        partidaDTO.setResultado(partida.getResultado());
        partidaDTO.setEstadio(partida.getEstadio());
        partidaDTO.setDataHora(partida.getDataHora());

        return partidaDTO;
    }
}
