package meli.com.apifut.Service;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Exceptions.*;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
            throw new CamposObrigatoriosException("Preencha todos os dados obrigatórios ",HttpStatus.BAD_REQUEST);
        } else if (timeDTO.getNome().length() < 2) {
            throw new DadosInvalidosException("O nome do time não pode ser nulo ou conter menos que 2 letras ",HttpStatus.BAD_REQUEST);
        } else if (!estadosBrasileiros.contains(timeDTO.getSiglaEstado())) {
            throw new DadosInvalidosException("A sigla de Estado informada não existe",HttpStatus.BAD_REQUEST);
        } else if (timeDTO.getDataCriacao().isAfter(LocalDateTime.now())) {
            throw new ConflitoDeDadosException("Não é possível criar um time em uma data no futuro",HttpStatus.BAD_REQUEST);
        } else if (timeRepository.findTimeDuplicado(timeDTO.getNome(), timeDTO.getSiglaEstado()) != null) {
            throw new EntidadeDuplicadaException("Já existe um clube com esse nome no mesmo estado",HttpStatus.CONFLICT);
        } else {
            Time time = converterEntidade(timeDTO);
            timeRepository.save(time);
        }

    }

    public void editarClube(Long id, TimeDTO timeDTO) {
        Time time = timeRepository.findTimeById(id);
        if (time == null){
            throw new EntidadeNaoEncontradaException("Não existe um time com o ID informado",HttpStatus.NOT_FOUND);
        }else if (timeRepository.findTimeDuplicado(timeDTO.getNome(), timeDTO.getSiglaEstado()) != null) {
            throw new EntidadeDuplicadaException("Já existe um time com mesmo nome cadastrado neste estado",HttpStatus.CONFLICT);
        }else if (timeRepository.findPrimeiraPartidaById(id) != null || timeDTO.getDataCriacao().isAfter(timeRepository.findPrimeiraPartidaById(id))){
            throw new ConflitoDeDadosException("A data de criação do time não pode ser após uma partida registrada em que ele ja participou",HttpStatus.CONFLICT);
        } else if (timeDTO.getDataCriacao().isAfter(LocalDateTime.now())){
            throw new DadosInvalidosException("A data de criação do time não pode ser no futuro",HttpStatus.BAD_REQUEST);
        }else if (!estadosBrasileiros.contains(timeDTO.getSiglaEstado())) {
            throw new DadosInvalidosException("A sigla de Estado informada não existe",HttpStatus.BAD_REQUEST);
        }else if (timeDTO.getNome().length() < 2){
            throw new DadosInvalidosException("O nome do time não deve ser menor que 2 caracteres",HttpStatus.BAD_REQUEST);
        }
            time.setNome(timeDTO.getNome());
            time.setSiglaEstado(timeDTO.getSiglaEstado());
            time.setDataCriacao(timeDTO.getDataCriacao());
            time.setStatus(timeDTO.getStatus());
            timeRepository.save(time);
        }

    public void inativarClube(Long id) {
        if (timeRepository.findTimeById(id) == null) {
            throw new EntidadeNaoEncontradaException("Não existe um time com o ID informado",HttpStatus.NOT_FOUND);
        } {
            Time time = timeRepository.findTimeById(id);
            time.setStatus(false);
            timeRepository.save(time);
        }
    }

    public TimeDTO buscarClubePorID(Long id) {
        if (timeRepository.findTimeById(id) == null) {
            throw new EntidadeNaoEncontradaException("Não existe um time com o ID informado",HttpStatus.NOT_FOUND);
        }
        return converterParaDTO(timeRepository.findTimeById(id));
    }

    public Page<TimeDTO> listarTimes(String nome, String siglaEstado, Boolean status, Pageable pageable) {
       Page<Time> timesFiltrados;
        if (nome == null && siglaEstado == null && status == null) {
            timesFiltrados = timeRepository.findAll(pageable);
        } else {
            timesFiltrados = timeRepository.findAllByNomeOrSiglaEstadoORStatus(
                    nome != null ? nome : "",
                    siglaEstado != null ? siglaEstado : "",
                    status,
                    pageable);
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
