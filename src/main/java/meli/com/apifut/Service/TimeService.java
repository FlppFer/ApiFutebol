package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service

public class TimeService {

    private static final Map<Integer, Time> listaTimes = new ConcurrentHashMap>();
    private static int IdAtual = 0;

    public List<Time> getAllTimes() {
        return new ArrayList<>(listaTimes.values());
    }

        public TimeDTO criarClube(TimeDTO timeDTO) {
        Time time = converterEntidade(timeDTO);
//        time = timeRepository.save(club);
            IdAtual += 1;
            int Id = IdAtual;
            listaTimes.put(Id, time);
        return converterDTO(time);
    }

    private Time converterEntidade(TimeDTO timeDTO) {
        Time time = new Time();
        time.setNome(timeDTO.getNome());
        time.setEstado(timeDTO.getEstado());
        time.setCriacao(timeDTO.getCriacao());
        time.setStatus(timeDTO.getStatus());
        return time;
    }


    private TimeDTO converterDTO(Time time) {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setNome(time.getNome());
        timeDTO.setEstado(time.getEstado());
        timeDTO.setCriacao(time.getCriacao());
        timeDTO.setStatus(time.getStatus());
        return timeDTO;

    }
    public TimeDTO atualizarClube(TimeDTO timeDTO) {
        timeDTO.setNome(timeDTO.getNome());
        timeDTO.setEstado(timeDTO.getEstado());
        timeDTO.setCriacao(timeDTO.getCriacao());
        timeDTO.setStatus(timeDTO.getStatus());
        return timeDTO;

    }
    public TimeDTO inativarClube(TimeDTO timeDTO) {
    timeDTO.setStatus(false);
    return timeDTO;
    }
    public TimeDTO buscarClubePorID(int Id) {
        return converterDTO(listaTimes.get(Id));
    }
}
