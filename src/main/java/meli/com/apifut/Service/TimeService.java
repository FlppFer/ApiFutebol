package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TimeService {
    static final Map<Long, Time> listaTimes = new ConcurrentHashMap<>();
    private static long IdAtualTime = 0;

    public TimeService() {
    }

    public List<Time> getAllTimes() {
        return new ArrayList<>(listaTimes.values());
    }

    public TimeDTO criarClube(TimeDTO timeDTO) {
        Time time = converterEntidade(timeDTO);

        long id = IdAtualTime;
        listaTimes.put(id, time);
        IdAtualTime += 1;

        return converterDTO(time);
    }

    public TimeDTO editarClube(TimeDTO timeDTO) {
        timeDTO.setNome(timeDTO.getNome());
        timeDTO.setEstado(timeDTO.getEstado());
        timeDTO.setCriacao(timeDTO.getCriacao());
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
                .filter(time -> (nome == null || time.getNome().toLowerCase().contains(nome.toLowerCase())))
                .filter(time -> (estado == null || time.getEstado().equalsIgnoreCase(estado)))
                .filter(time -> (status == null || time.isAtivo() == status))
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
        Comparator<Time> comparator = Comparator.comparing(Time::getNome); // Default sorting by nome

        switch (sortBy) {
            case "estado":
                comparator = Comparator.comparing(Time::getEstado);
                break;
            case "status":
                comparator = Comparator.comparing(Time::isAtivo);
                break;
        }

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return comparator;
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


}