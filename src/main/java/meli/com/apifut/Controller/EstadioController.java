package meli.com.apifut.Controller;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Service.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadios")
public class EstadioController {

    private final EstadioService estadioService;

    @Autowired
    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<EstadioDTO> criarEstadio(@RequestBody EstadioDTO estadioDTO) {
        EstadioDTO novoEstadio = estadioService.criarEstadio(estadioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstadio);
    }

    @PutMapping("/editar")
    public ResponseEntity<EstadioDTO> editarEstadio(@RequestBody EstadioDTO estadioDTO) {
        EstadioDTO estadioEditado = estadioService.editarNomeDOEstadio(estadioDTO);
        return ResponseEntity.ok(estadioEditado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EstadioDTO>> listarEstadios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<EstadioDTO> estadios = estadioService.listarEstadios(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(estadios);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Estadio> buscarEstadioPorId(@PathVariable long id) {
        Estadio estadio = estadioService.buscarEstadioPorID(id);
        if (estadio != null) {
            return ResponseEntity.ok(estadio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
