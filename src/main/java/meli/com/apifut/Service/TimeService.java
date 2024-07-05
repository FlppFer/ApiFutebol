package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public TimeDTO criarClube(TimeDTO timeDTO) {

        Time time = converterEntidade(timeDTO);


        return converterDTO(time);
    }

    public TimeDTO editarClube(TimeDTO timeDTO) {
        timeDTO.setNomeDoTime(timeDTO.getNomeDoTime());
        timeDTO.setSiglaEstado(timeDTO.getSiglaEstado());
        timeDTO.setDataCriacao(timeDTO.getDataCriacao());
        timeDTO.setStatus(timeDTO.getStatus());
        return timeDTO;
    }

    public Time inativarClube(long id) {
        buscarClubePorID(id).setStatus(false);
        return buscarClubePorID(id);
    }

    public Time buscarClubePorID(long id) {
        return listaTimes.get(id);
    }

    public List<TimeDTO> listarTimes(String nome, String estado, Boolean status, int page, int size, String sortBy, String sortDirection) {
        List<Time> timesFiltrados = listaTimes.values().stream()
                .filter(time -> (nome == null || time.getNomeDoTime().toLowerCase().contains(nome.toLowerCase())))
                .filter(time -> (estado == null || time.getSiglaEstado().equalsIgnoreCase(estado)))
                .filter(time -> (status == null || time.isStatus() == status))
                .sorted(getComparator(sortBy, sortDirection))
                .collect(Collectors.toList());

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, timesFiltrados.size());

        List<Time> timesPaginados = timesFiltrados.subList(startIndex, endIndex);

        return timesPaginados.stream()
                .map(this::converterDTO)
                .collect(Collectors.toList());
    }

    private Comparator<Time> getComparator(String sortBy, String sortDirection) {
        Comparator<Time> comparator = Comparator.comparing(Time::getNomeDoTime); // Default sorting by nome

        switch (sortBy) {
            case "estado":
                comparator = Comparator.comparing(Time::getSiglaEstado);
                break;
            case "status":
                comparator = Comparator.comparing(Time::isStatus);
                break;
        }

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return comparator;
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