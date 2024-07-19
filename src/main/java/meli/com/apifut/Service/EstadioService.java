package meli.com.apifut.Service;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Exceptions.CamposObrigatoriosException;
import meli.com.apifut.Exceptions.EntidadeDuplicadaException;
import meli.com.apifut.Exceptions.EntidadeNaoEncontradaException;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Repository.EstadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    public void criarEstadio(EstadioDTO estadioDTO) {
        if (estadioDTO.getNome().length() < 3) {
            throw new CamposObrigatoriosException("O nome do estádio não deve ser ser e deve ter no mínimo, 3 caracteres", HttpStatus.BAD_REQUEST);
        } else if (estadioRepository.findEstadioByNome(estadioDTO.getNome()) != null){
            throw new EntidadeDuplicadaException("Já existe um estádio com o nome informado", HttpStatus.CONFLICT);
        }   {
            Estadio estadio = converterEntidade(estadioDTO);
            estadioRepository.save(estadio);
        }
    }

    public void editarEstadio(Long id, EstadioDTO estadioDTO) {
        Estadio estadio = estadioRepository.findEstadioById(id);
        if (estadio == null){
            throw new EntidadeNaoEncontradaException("Estádio com ID informado não encontrado", HttpStatus.NOT_FOUND);
        }else if (estadioDTO.getNome().length() < 3 || (estadioDTO.getNome() == null)){
            throw new CamposObrigatoriosException("O nome do estádio não deve ser nulo e deve ter no mínimo, 3 caracteres", HttpStatus.BAD_REQUEST);
        } else if (estadioRepository.findEstadioByNome(estadioDTO.getNome()) != null){
            throw new EntidadeDuplicadaException("Já existe um estádio com o nome informado", HttpStatus.CONFLICT);
        }   {
            estadio.setNome(estadioDTO.getNome());
            estadioRepository.save(estadio);
        }
    }

    public EstadioDTO buscarEstadioPorID(Long id) {
        if (!estadioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Estádio com ID informado não encontrado", HttpStatus.NOT_FOUND);
        } else {
            return converterDTO(estadioRepository.findEstadioById(id));
        }
    }

    public Page<EstadioDTO> listarEstadios(Pageable pageable) {
        Page<EstadioDTO> listarEstadios;
        Page<Estadio> listaTotalEstadios = estadioRepository.findAll(pageable);
        if (listaTotalEstadios.isEmpty()) {
            listarEstadios = Page.empty();
        } else {
            listarEstadios = estadioRepository.findAll(pageable).map(this::converterDTO);
        }
        return listarEstadios;
    }


    public Estadio converterEntidade(EstadioDTO estadioDTO) {
        Estadio estadio = new Estadio();
        estadio.setNome(estadioDTO.getNome());

        return estadio;
    }

    public EstadioDTO converterDTO(Estadio estadio) {
        EstadioDTO estadioDTO = new EstadioDTO();

        estadioDTO.setId(estadio.getId());
        estadioDTO.setNome(estadio.getNome());


        return estadioDTO;
    }

}
