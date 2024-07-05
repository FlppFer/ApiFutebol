package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public Time criarClube(TimeDTO timeDTO) {
        Time time = converterEntidade(timeDTO);
        return timeRepository.save(time);
    }

    public void editarClube(Long id, TimeDTO timeDTO) {
        Optional<Time> optionalTime = timeRepository.findById(id);

        if (optionalTime.isPresent()) {
            Time time = optionalTime.get();

            time.setNomeDoTime(timeDTO.getNomeDoTime());
            time.setSiglaEstado(timeDTO.getSiglaEstado());
            time.setDataCriacao(timeDTO.getDataCriacao());
            time.setStatus(timeDTO.getStatus());

            timeRepository.save(time);
        } else {
            throw new NoSuchElementException();
        }

    }

    public void inativarClube(long id) {
        Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
            buscarClubePorID(id).setStatus(false);
            timeRepository.save(optionalTime.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public Time buscarClubePorID(Long id) {
        Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isPresent()) {
        return optionalTime.get();
        }
        throw new NoSuchElementException();
    }

    public Page<Time> listarTimes(String nome, String estado, Boolean status, Pageable pageable) {
        Page<Time> timesPage;
        if (nome != null || estado != null || status != null) {
            timesPage = timeRepository.findByNomeDoTimeContainingIgnoreCaseAndSiglaEstadoIgnoreCaseAndStatus(nome, estado, status, pageable);
        } else {
            timesPage = timeRepository.findAll(pageable);
        }
        return timesPage;
    }



    private Time converterEntidade(TimeDTO timeDTO) {
        Time time = new Time();
        time.setNomeDoTime(timeDTO.getNomeDoTime());
        time.setSiglaEstado(timeDTO.getSiglaEstado());
        time.setDataCriacao(timeDTO.getDataCriacao());
        time.setStatus(timeDTO.getStatus());
        return time;
    }

    private TimeDTO converterDTO(Time time) {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setNomeDoTime(time.getNomeDoTime());
        timeDTO.setSiglaEstado(time.getSiglaEstado());
        timeDTO.setDataCriacao(time.getDataCriacao());
        timeDTO.setStatus(time.getStatus());
        return timeDTO;
    }


}