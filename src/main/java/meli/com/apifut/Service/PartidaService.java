package meli.com.apifut.Service;

import meli.com.apifut.Model.Partida;
import meli.com.apifut.DTO.PartidaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {

    private static long IdAtualPartida = 0;

    public PartidaDTO criarNovaPartida(PartidaDTO novaPartidaDTO) {
        Partida partida = converterEntidade(novaPartidaDTO);

        long id = IdAtualPartida;
        listaPartidas.put(id, novaPartidaDTO);
        IdAtualPartida += 1;

        return converterDTO(partida);
    }

    public PartidaDTO editarPartida(long id, PartidaDTO novaPartidaDTO) {
        PartidaDTO partidaExistente = buscarPartidaPorId(id);

        if (partidaExistente != null) {
            partidaExistente.setTimeCasa(novaPartidaDTO.getTimeCasa());
            partidaExistente.setTimeVisitante(novaPartidaDTO.getTimeVisitante());
            partidaExistente.setDataHora(novaPartidaDTO.getDataHora());
            partidaExistente.setResultado(novaPartidaDTO.getResultado());
            partidaExistente.setEstadio(novaPartidaDTO.getEstadio());

            listaPartidas.put(id, partidaExistente);

            return partidaExistente;
        }

        return null;
    }

    public PartidaDTO removerPartidaPorID(long id) {
        PartidaDTO partidaRemovida = listaPartidas.remove(id);
        return partidaRemovida;
    }

    public PartidaDTO buscarPartidaPorId(long id) {
        return listaPartidas.get(id);
    }

    public List<PartidaDTO> listarTodasAsPartidas() {
        return new ArrayList<>(listaPartidas.values());
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
