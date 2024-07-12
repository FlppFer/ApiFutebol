package meli.com.apifut.Controller;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Service.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estadios")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;


    @PostMapping("/criar")
    public ResponseEntity<?> criarEstadio(@RequestBody EstadioDTO estadioDTO) {
        try {
            estadioService.criarEstadio(estadioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("O Estadio '" + estadioDTO.getNome() + "' foi criado com sucesso");
        } catch (Exception e) {
            String mensagemErro = "O Estadio não foi criado, verifique se todos os campos foram preenchidos e de forma correta";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
        }
    }

    @PutMapping("/editarEstadio/{id}")
    public ResponseEntity<?> editarEstadio(@PathVariable Long id, @RequestBody EstadioDTO estadioDTO) {
        try {
            estadioService.editarEstadio(id,estadioDTO);
            return ResponseEntity.status(HttpStatus.OK).body("O Estadio '" + estadioDTO.getNome() + "' foi editado com sucesso");
        } catch (Exception e){
            String mensagemErro = "O Estadio não foi editado, verifique se todos os campos foram preenchidos e de forma correta";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

        }
    }

    @GetMapping("/buscarEstadio/{id}")
    public ResponseEntity<?> buscarEstadioPorId(@PathVariable Long id) {
        try{
           Estadio estadio = estadioService.buscarEstadioPorID(id);
            return ResponseEntity.ok(estadio);
        } catch (Exception e){
            String mensagemErro = "O Estadio não foi encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<Estadio>> listarEstadios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Page<Estadio> estadios = estadioService.listarEstadios(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
        return ResponseEntity.ok(estadios);
    }

}
