package meli.com.apifut.Controller;

import meli.com.apifut.DTO.EstadioDTO;
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


    @PostMapping("/criarEstadio")
    public ResponseEntity<?> criarEstadio(@RequestBody EstadioDTO estadioDTO) {
            estadioService.criarEstadio(estadioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("O Estadio '" + estadioDTO.getNome() + "' foi criado com sucesso");
    }

    @PutMapping("/editarEstadio/")
    public ResponseEntity<?> editarEstadio(@RequestParam Long id, @RequestBody EstadioDTO estadioDTO) {
            estadioService.editarEstadio(id,estadioDTO);
            return ResponseEntity.status(HttpStatus.OK).body("O Estadio foi editado com sucesso");
    }

    @GetMapping("/buscarEstadioPorId")
    public ResponseEntity<?> buscarEstadioPorId(@RequestParam Long id) {
            EstadioDTO estadioDTO = estadioService.buscarEstadioPorID(id);
            return ResponseEntity.ok(estadioDTO);
    }

    @GetMapping("/listarEstadios")
    public ResponseEntity<Page<EstadioDTO>> listarEstadios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Page<EstadioDTO> listarEstadios = estadioService.listarEstadios(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
        return ResponseEntity.ok(listarEstadios);
    }

}
