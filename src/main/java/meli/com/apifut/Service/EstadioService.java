package meli.com.apifut.Service;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Model.Estadio;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class EstadioService {

    static final Map<Long, Estadio> listaEstadios = new ConcurrentHashMap<>();
    private static long IdAtualEstadio = 0;

    public EstadioDTO criarEstadio(EstadioDTO estadioDTO) {
        Estadio estadio = converterEntidade(estadioDTO);

        long idEstadio = IdAtualEstadio;
        listaEstadios.put(idEstadio, estadio);
        IdAtualEstadio += 1;

        return converterDTO(estadio);
    }

    public EstadioDTO editarNomeDOEstadio(EstadioDTO estadioDTO) {
        estadioDTO.setNomeDoEstadio(estadioDTO.getNomeDoEstadio());
        return estadioDTO;
    }

    public Estadio buscarEstadioPorID(long idEstadio) {
        return listaEstadios.get(idEstadio);
    }

    public List<EstadioDTO> listarEstadios(int page, int size, String sortBy, String sortDirection) {
        Comparator<Estadio> comparator = getComparator(sortBy, sortDirection);

        List<Estadio> estadiosOrdenados = listaEstadios.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, estadiosOrdenados.size());

        List<Estadio> estadiosPaginados = estadiosOrdenados.subList(startIndex, endIndex);

        return estadiosPaginados.stream()
                .map(this::converterDTO)
                .collect(Collectors.toList());
    }

    private Comparator<Estadio> getComparator(String sortBy, String sortDirection) {
        Comparator<Estadio> comparator = Comparator.comparing(Estadio::getNomeDoEstadio);

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    private Estadio converterEntidade(EstadioDTO estadioDTO) {
        Estadio estadio = new Estadio();
        estadio.setNomeDoEstadio(estadioDTO.getNomeDoEstadio());

        return estadio;
    }

    private EstadioDTO converterDTO(Estadio estadio) {
        EstadioDTO estadioDTO = new EstadioDTO();
        estadioDTO.setNomeDoEstadio(estadio.getNomeDoEstadio());

        return estadioDTO;
    }

}
