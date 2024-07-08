package meli.com.apifut.Controller;

import meli.com.apifut.DTO.TimeDTO;
import meli.com.apifut.Model.Time;
import meli.com.apifut.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/times")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarClube(@RequestBody TimeDTO timeDTO) {
        try {
            timeService.criarClube(timeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("O Clube '" + timeDTO.getNome() + "' foi criado com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O Clube não foi criado, verifique se todos os campos foram preenchidos e de forma correta");
        }

    }

    @PutMapping("/editarTime/{id}")
    public ResponseEntity<?> editarClube(@PathVariable Long id, @RequestBody TimeDTO timeDTO) {
        try {
            timeService.editarClube(id, timeDTO);
            return ResponseEntity.status(HttpStatus.OK).body("O Clube '" + timeDTO.getNome() + "' foi editado com sucesso");
        } catch (NoSuchElementException e) {
            String mensagemErro = "Time não encontrado com o id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }


    @DeleteMapping("/inativarTime/{id}")
    public ResponseEntity<?> inativarClube(@PathVariable long id) {
        try {
            timeService.inativarClube(id);
            return ResponseEntity.noContent().build();
        }catch(NoSuchElementException e){
            String mensagemErro = "Time não encontrado com o id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }

    }

    @GetMapping("/buscarTimePorId/{id}")
    public ResponseEntity<?> buscarClube(@PathVariable long id) {
        try {
            Time time = timeService.buscarClubePorID(id);
            return ResponseEntity.ok(time);
        }catch (NoSuchElementException e){
            String mensagemErro = "Time não encontrado com o id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }
//fazer excessão
    @GetMapping("/listarTimes")
    public ResponseEntity<Page<Time>> listarTimes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<Time> times = timeService.listarTimes(
                nome,
                estado,
                status,
                PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy)
        );
        return ResponseEntity.ok(times);
    }


}
