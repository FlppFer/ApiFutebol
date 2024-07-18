package meli.com.apifut.Controller;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/times")
public class  TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping("/criarTime")
    public ResponseEntity<?> criarClube(@RequestBody TimeDTO timeDTO) {
        timeService.criarClube(timeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("O Clube '" + timeDTO.getNome() + "' foi criado com sucesso");
    }


    @PutMapping("/editarTime")
    public ResponseEntity<?> editarClube(@RequestParam Long id, @RequestBody TimeDTO timeDTO) {
            timeService.editarClube(id, timeDTO);
            return ResponseEntity.status(HttpStatus.OK).body("O Clube '" + timeDTO.getNome() + "' foi editado com sucesso");

    }


    @DeleteMapping("/inativarTime")
    public ResponseEntity<?> inativarClube(@RequestParam long id) {
        timeService.inativarClube(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/buscarTimePorId")
    public ResponseEntity<TimeDTO> buscarClube(@RequestParam long id) {
            TimeDTO timeBusca = timeService.buscarClubePorID(id);
            return ResponseEntity.ok(timeBusca);
    }

    @GetMapping("/listarTimes")
    public ResponseEntity<Page<TimeDTO>> listarTimes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String siglaEstado,
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<TimeDTO> times = timeService.listarTimes(nome, siglaEstado, status,
                PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));

        return ResponseEntity.ok(times);
    }
}
