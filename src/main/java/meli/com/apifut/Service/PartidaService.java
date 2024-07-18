package meli.com.apifut.Service;

import meli.com.apifut.Exceptions.*;
import meli.com.apifut.Exceptions.CamposObrigatoriosException;
import meli.com.apifut.Exceptions.EntidadeDuplicadaException;
import meli.com.apifut.Model.Partida;
import meli.com.apifut.DTO.PartidaDTO;
import meli.com.apifut.Repository.EstadioRepository;
import meli.com.apifut.Repository.PartidaRepository;
import meli.com.apifut.Repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private EstadioRepository estadioRepository;


    public void criarNovaPartida(PartidaDTO partidaDTO) {
        if (partidaDTO.getTimeCasa() == null || partidaDTO.getTimeVisitante() == null || partidaDTO.getEstadio() == null || partidaDTO.getGolsTimeCasa() == null || partidaDTO.getGolsTimeVisitante() == null || partidaDTO.getDataHoraPartida() == null) {
            throw new CamposObrigatoriosException("Preencha todos os campos obrigatórios!", HttpStatus.BAD_REQUEST);
        } else if (partidaDTO.getTimeCasa() == partidaDTO.getTimeVisitante()) {
            throw new EntidadeDuplicadaException("O time da casa e o time visitante não podem ser iguais!", HttpStatus.BAD_REQUEST);
        } else if (!timeRepository.existsById(partidaDTO.getTimeCasa().getId()) && timeRepository.existsById(partidaDTO.getTimeVisitante().getId())) {
            throw new EntidadeNaoEncontradaException("O time da casa ou o time visitante não existe!", HttpStatus.BAD_REQUEST);
        } else if (!estadioRepository.existsById(partidaDTO.getEstadio().getId())) {
            throw new EntidadeNaoEncontradaException("O estádio não foi encontrado!", HttpStatus.BAD_REQUEST);
        } else if (partidaDTO.getGolsTimeCasa() < 0 || partidaDTO.getGolsTimeVisitante() < 0) {
            throw new DadosInvalidosException("Os gols não podem ser negativos!", HttpStatus.BAD_REQUEST);
        } else if (partidaDTO.getDataHoraPartida().isAfter(LocalDateTime.now())) {
            throw new ConflitoDeDadosException("A data da partida não pode ser no futuro!", HttpStatus.BAD_REQUEST);
        } else if (partidaDTO.getDataHoraPartida().isBefore(timeRepository.findDataCriacaoTime(partidaDTO.getTimeCasa().getId())) || partidaDTO.getDataHoraPartida().isBefore(timeRepository.findDataCriacaoTime(partidaDTO.getTimeVisitante().getId()))){
            throw new ConflitoDeDadosException("A data da partida não pode ser antes da data de criação de um dos times!", HttpStatus.CONFLICT);
        } else if (timeRepository.isTimeAtivo((partidaDTO.getTimeCasa()).getId()) == null || timeRepository.isTimeAtivo((partidaDTO.getTimeVisitante()).getId()) == null) {
            throw new TimeInativoException("O time da casa ou o time visitante não está ativo!", HttpStatus.BAD_REQUEST);
        } else if(partidaRepository.findConflitoHorarioPartida(partidaDTO.getDataHoraPartida(), partidaDTO.getTimeCasa().getId()) != null && partidaRepository.findConflitoHorarioPartida(partidaDTO.getDataHoraPartida(), partidaDTO.getTimeVisitante().getId()) != null){
            throw new ConflitoDeDadosException("Um dos times jogou uma partida recentemente!", HttpStatus.CONFLICT);
        }else if (partidaRepository.findPartidaByDataAndEstadio(partidaDTO.getDataHoraPartida(), partidaDTO.getEstadio().getId()) != null){
            throw new ConflitoDeDadosException("Já existe uma partida marcada para esse estádio nessa data!", HttpStatus.CONFLICT);
        }
        Partida partida = converterEntidade(partidaDTO);
        partidaRepository.save(partida);


    }

    public void editarPartida(Long id, PartidaDTO partidaDTO) {
        if (!partidaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("A partida não foi encontrada!", HttpStatus.NOT_FOUND);
        }else if(partidaDTO.getId() == null || partidaDTO.getTimeCasa() == null || partidaDTO.getTimeVisitante() == null || partidaDTO.getEstadio() == null || partidaDTO.getGolsTimeCasa() == null || partidaDTO.getGolsTimeVisitante() == null || partidaDTO.getDataHoraPartida() == null){
            throw new CamposObrigatoriosException("Preencha todos os campos obrigatórios!", HttpStatus.BAD_REQUEST);
        }else if (partidaDTO.getTimeCasa() == partidaDTO.getTimeVisitante()) {
            throw new EntidadeDuplicadaException("O time da casa e o time visitante não podem ser iguais!", HttpStatus.BAD_REQUEST);
        } else if(!timeRepository.existsById(partidaDTO.getTimeCasa().getId()) && timeRepository.existsById(partidaDTO.getTimeVisitante().getId())) {
            throw new EntidadeNaoEncontradaException("O time da casa ou o time visitante não existe!", HttpStatus.BAD_REQUEST);
        } else if (!estadioRepository.existsById(partidaDTO.getEstadio().getId())) {
            throw new EntidadeNaoEncontradaException("O estádio não foi encontrado!", HttpStatus.BAD_REQUEST);
        } else if (partidaDTO.getGolsTimeCasa() < 0 || partidaDTO.getGolsTimeVisitante() < 0){
            throw new DadosInvalidosException("Os gols não podem ser negativos!", HttpStatus.BAD_REQUEST);
        }else if (partidaDTO.getDataHoraPartida().isAfter(LocalDateTime.now())) {
            throw new ConflitoDeDadosException("A data da partida não pode ser no futuro!", HttpStatus.BAD_REQUEST);
        }else if (partidaDTO.getDataHoraPartida().isBefore(timeRepository.findDataCriacaoTime(partidaDTO.getTimeCasa().getId())) || partidaDTO.getDataHoraPartida().isBefore(timeRepository.findDataCriacaoTime(partidaDTO.getTimeVisitante().getId()))) {
            throw new ConflitoDeDadosException("A data da partida não pode ser antes da data de criação de um dos times!", HttpStatus.CONFLICT);
        }else if (timeRepository.isTimeAtivo((partidaDTO.getTimeCasa()).getId()) == null || timeRepository.isTimeAtivo((partidaDTO.getTimeVisitante()).getId()) == null) {
            throw new TimeInativoException("O time da casa ou o time visitante não está ativo!", HttpStatus.CONFLICT);
        }else if(partidaRepository.findConflitoHorarioPartida(partidaDTO.getDataHoraPartida(), partidaDTO.getTimeCasa().getId()) != null && partidaRepository.findConflitoHorarioPartida(partidaDTO.getDataHoraPartida(), partidaDTO.getTimeVisitante().getId()) != null){
            throw new ConflitoDeDadosException("Um dos times jogou uma partida recentemente!", HttpStatus.CONFLICT);
        }else if (partidaRepository.findPartidaByDataAndEstadio(partidaDTO.getDataHoraPartida(), partidaDTO.getEstadio().getId()) != null){
            throw new ConflitoDeDadosException("Já existe uma partida marcada para esse estádio nessa data!", HttpStatus.CONFLICT);
        }
            Partida partida = partidaRepository.findPartidaById(id);
            partida.setTimeCasa(partidaDTO.getTimeCasa());
            partida.setTimeVisitante(partidaDTO.getTimeVisitante());
            partida.setGolsTimeCasa(partidaDTO.getGolsTimeCasa());
            partida.setGolsTimeVisitante(partidaDTO.getGolsTimeVisitante());
            partida.setResultado(calcularResultado(partidaDTO.getGolsTimeCasa(), partidaDTO.getGolsTimeVisitante()));
            partida.setEstadio(partidaDTO.getEstadio());
            partida.setDataHoraPartida(partidaDTO.getDataHoraPartida());
            partidaRepository.save(partida);

    }

    public void removerPartidaPorID(long id) {
        if (!partidaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Uma partida com esse id não foi encontrada!", HttpStatus.NOT_FOUND);
        } else {
            partidaRepository.deleteById(id);
        }
    }

    public PartidaDTO buscarPartidaPorId(Long id) {
        if (!partidaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Uma partida com esse id não foi encontrada!",HttpStatus.NOT_FOUND);
        }
        return converterDTO(partidaRepository.findPartidaById(id));
    }


    public Page<PartidaDTO> listarPartidas(Long timeId, Long estadioId, Pageable pageable) {
        Page<Partida> partidasFiltradas;
        if (timeId == null && estadioId == null) {
            partidasFiltradas = partidaRepository.findAll(pageable);
        } else{
            partidasFiltradas = partidaRepository.findAllByTimeCasaIdOrTimeVisitanteIdOrEstadioId(
                    timeId != null ? timeId : 0,
                    estadioId != null ? estadioId : 0,
                    pageable);
        }
        if (partidasFiltradas.isEmpty()){
            return Page.empty();
        }return partidasFiltradas.map(this::converterDTO);
    }



    private Partida converterEntidade(PartidaDTO partidaDTO) {
        Partida partida = new Partida();

        partida.setTimeCasa(partidaDTO.getTimeCasa());
        partida.setTimeVisitante(partidaDTO.getTimeVisitante());
        partida.setEstadio(partidaDTO.getEstadio());
        partida.setDataHoraPartida(partidaDTO.getDataHoraPartida());
        partida.setGolsTimeCasa(partidaDTO.getGolsTimeCasa());
        partida.setGolsTimeVisitante(partidaDTO.getGolsTimeVisitante());
        partida.setResultado(calcularResultado(partidaDTO.getGolsTimeCasa(), partidaDTO.getGolsTimeVisitante()));

        return partida;
    }

    private PartidaDTO converterDTO(Partida partida) {
        PartidaDTO partidaDTO = new PartidaDTO();
        partidaDTO.setId(partida.getId());
        partidaDTO.setTimeCasa(partida.getTimeCasa());
        partidaDTO.setTimeVisitante(partida.getTimeVisitante());
        partidaDTO.setResultado(partida.getResultado());
        partidaDTO.setEstadio(partida.getEstadio());
        partidaDTO.setDataHoraPartida(partida.getDataHoraPartida());

        return partidaDTO;
    }

    private String calcularResultado(Long golsTimeCasa, Long golsTimeVisitante) {
        if (golsTimeCasa > golsTimeVisitante) {
            return "Vitória do time da casa";
        } else if (golsTimeCasa < golsTimeVisitante) {
            return "Vitória do time visitante";
        } else {
            return "Empate";
        }
    }
}
