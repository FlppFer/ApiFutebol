package meli.com.apifut.Controller;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @PostMapping("/criar")
    public ResponseEntity<TimeDTO> criarClube(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeService.criarClube(timeDTO));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<TimeDTO> atualizarClube(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.ok(timeService.editarClube(timeDTO));
    }

    @DeleteMapping("/inativar")
    public ResponseEntity<TimeDTO> inativarClube(@PathVariable long id) {
        timeService.inativarClube(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<TimeDTO> buscarClube(@PathVariable long id) {
        Time timeDTO = timeService.buscarClubePorID(id);
        if (timeDTO != null) {
            return ResponseEntity.ok(new TimeDTO(timeDTO));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/listarPorParametro")
    public ResponseEntity<List<TimeDTO>> listarTimes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        List<TimeDTO> times = timeService.listarTimes(nome, estado, status, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(times);
    }
    @GetMapping("/listarTodosOsTimes")
    public ResponseEntity<List<Time>> listarTodosOsTimes() {
        List<Time> times = timeService.getAllTimes();
        return ResponseEntity.ok(times);

    }

}
