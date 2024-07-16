package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Exceptions.*;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TimeService {

    public List<String> estadosBrasileiros = new ArrayList<>();

    {
        estadosBrasileiros.add("AC"); // Acre
        estadosBrasileiros.add("AL"); // Alagoas
        estadosBrasileiros.add("AP"); // Amapá
        estadosBrasileiros.add("AM"); // Amazonas
        estadosBrasileiros.add("BA"); // Bahia
        estadosBrasileiros.add("CE"); // Ceará
        estadosBrasileiros.add("DF"); // Distrito Federal
        estadosBrasileiros.add("ES"); // Espírito Santo
        estadosBrasileiros.add("GO"); // Goiás
        estadosBrasileiros.add("MA"); // Maranhão
        estadosBrasileiros.add("MT"); // Mato Grosso
        estadosBrasileiros.add("MS"); // Mato Grosso do Sul
        estadosBrasileiros.add("MG"); // Minas Gerais
        estadosBrasileiros.add("PA"); // Pará
        estadosBrasileiros.add("PB"); // Paraíba
        estadosBrasileiros.add("PR"); // Paraná
        estadosBrasileiros.add("PE"); // Pernambuco
        estadosBrasileiros.add("PI"); // Piauí
        estadosBrasileiros.add("RJ"); // Rio de Janeiro
        estadosBrasileiros.add("RN"); // Rio Grande do Norte
        estadosBrasileiros.add("RS"); // Rio Grande do Sul
        estadosBrasileiros.add("RO"); // Rondônia
        estadosBrasileiros.add("RR"); // Roraima
        estadosBrasileiros.add("SC"); // Santa Catarina
        estadosBrasileiros.add("SP"); // São Paulo
        estadosBrasileiros.add("SE"); // Sergipe
        estadosBrasileiros.add("TO"); // Tocantins
    }

    @Autowired
    private TimeRepository timeRepository;

    public void criarClube(TimeDTO timeDTO) {
        if (timeDTO.getNome() == null || timeDTO.getSiglaEstado() == null || timeDTO.getDataCriacao() == null) {
            throw new CamposObrigatoriosException(HttpStatus.BAD_REQUEST);
        } else if (timeDTO.getNome().length() < 2) {
            throw new NomeTimeInvalidoException(HttpStatus.BAD_REQUEST);
        } else if (!estadosBrasileiros.contains(timeDTO.getSiglaEstado())) {
            throw new SiglaInvalidaException(HttpStatus.BAD_REQUEST);
        } else if (timeDTO.getDataCriacao().isAfter(LocalDateTime.now())) {
            throw new DataConflitoException(HttpStatus.BAD_REQUEST);
        } else if (timeRepository.findTimeDuplicado(timeDTO.getNome(), timeDTO.getSiglaEstado()) != null) {
            throw new TimeDuplicadoException(HttpStatus.CONFLICT);
        } else {
            Time time = converterEntidade(timeDTO);
            timeRepository.save(time);
        }

    }

    public void editarClube(Long id, TimeDTO timeDTO) {
        Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isEmpty()) {
            throw new EntidadeNaoEncontradaException(HttpStatus.NOT_FOUND);
        } else if (timeDTO.getNome().length() < 2) {
            throw new NomeTimeInvalidoException(HttpStatus.BAD_REQUEST);
        } else if (!estadosBrasileiros.contains(timeDTO.getSiglaEstado())) {
            throw new SiglaInvalidaException(HttpStatus.BAD_REQUEST);
        } else if (timeDTO.getDataCriacao().isAfter(timeRepository.findPrimeiraPartida(id))) {
            throw new DataConflitoException(HttpStatus.CONFLICT);
        } else if (timeRepository.findTimeDuplicado(timeDTO.getNome(), timeDTO.getSiglaEstado()) != null) {
            throw new TimeDuplicadoException(HttpStatus.CONFLICT);
        }
    }

    public void inativarClube(long id) {
        Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isEmpty()) {
            throw new EntidadeNaoEncontradaException(HttpStatus.NOT_FOUND);
        }
        Time time = optionalTime.get();
        time.setStatus(false);
        timeRepository.save(time);

    }

    public TimeDTO buscarClubePorID(Long id) {
        Optional<Time> optionalTime = timeRepository.findById(id);
        if (optionalTime.isEmpty()) {
            throw new EntidadeNaoEncontradaException(HttpStatus.NOT_FOUND);
        }
        return converterParaDTO(optionalTime.get());
    }

    public Page<TimeDTO> listarTimes(String nome, String estado, Boolean status, Pageable pageable) {
       Page<Time> timesFiltrados;
        if (nome == null && estado == null && status == null) {
            timesFiltrados = timeRepository.findAll(pageable);
        } else {
            timesFiltrados = timeRepository.findAllByNomeContainingAndSiglaEstadoContainingAndStatus(
                    nome != null ? nome : "",
                    estado != null ? estado : "",
                    status,
                    pageable
            );

        }
        if (timesFiltrados.isEmpty()) {
            return Page.empty();
        }
        return timesFiltrados.map(this::converterParaDTO);
    }

    public TimeDTO converterParaDTO(Time time) {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setId(time.getId());
        timeDTO.setStatus(time.getStatus());
        timeDTO.setNome(time.getNome());
        timeDTO.setSiglaEstado(time.getSiglaEstado());
        timeDTO.setDataCriacao(time.getDataCriacao());
        timeDTO.setStatus(time.getStatus());
        return timeDTO;
    }

    private Time converterEntidade(TimeDTO timeDTO) {
        Time time = new Time();
        time.setNome(timeDTO.getNome());
        time.setSiglaEstado(timeDTO.getSiglaEstado());
        time.setDataCriacao(timeDTO.getDataCriacao());
        time.setStatus(timeDTO.getStatus());
        return time;
    }


}