package meli.com.apifut.Controller;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController


@RequestMapping
public class TimeController {
    @Autowired
    private TimeService timeService;

    @PostMapping("/criar")
    public ResponseEntity criarClube(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeService.criarClube(timeDTO));
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarClube(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.ok(timeService.atualizarClube(timeDTO));
    }

    @DeleteMapping("/inativar")
    public ResponseEntity inativarClube(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(timeService.inativarClube(timeDTO));
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity buscarClube(@RequestBody int Id) {
        return ResponseEntity.ok(timeService.buscarClubePorID(Id));
    }
}
