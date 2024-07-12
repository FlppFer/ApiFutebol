package meli.com.apifut.Controller;

import meli.com.apifut.DTO.PartidaDTO;
import meli.com.apifut.Model.Partida;
import meli.com.apifut.Service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/criarPartida")
    public ResponseEntity<?> criarNovaPartida(@RequestBody PartidaDTO partidaDTO) {
        try {
            partidaService.criarNovaPartida(partidaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("A partida entre " + partidaDTO.getTimeCasa().getNome() + "e o time " + partidaDTO.getTimeVisitante().getNome() + "foi registrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("A partida não foi registrada, verifique se todos os campos foram preenchidos corretamente!" + e);
        }

    }

    @DeleteMapping("/deletarPartidaPorID/{id}")
    public ResponseEntity<?> deletarPartida(@PathVariable Long id) {
        try {
            partidaService.removerPartidaPorID(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A partida não foi encontrada!");

        }
    }

    @PutMapping("/editarPartidaPorId/{id}")
    public ResponseEntity<?> editarPartida(@PathVariable long id, @RequestBody PartidaDTO partidaDTO) {
        try {
            partidaService.editarPartida(id, partidaDTO);
            return ResponseEntity.status(HttpStatus.OK).body("A partida entre " + partidaDTO.getTimeCasa() + " e "+ partidaDTO.getTimeVisitante() + " foi editada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A partida não foi encontrada ou um dos campos não foi preenchido corretamente!");
        }

    }

    @GetMapping("/buscarPartidaPorId/{id}")
    public ResponseEntity<?> buscarPartidaPorId(@PathVariable long id) {
        try {
            Partida partida = partidaService.buscarPartidaPorId(id);
            return ResponseEntity.ok(partida);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A partida com o id digitado não foi encontrada!");
        }
    }

    @GetMapping("/buscarPartidasPorParametro/")
    public ResponseEntity<?> listarPartidas(
            @RequestParam(required = false) Long partidaId,
            @RequestParam(required = false) Long timeId,
            @RequestParam(required = false) Long estadioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "time") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection   ){

            Page<Partida> partidas = partidaService.buscarPartidaOuPorTimeOuEstadio(
                    partidaId,
                    timeId,
                    estadioId,
                    PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy))
            );
            return ResponseEntity.ok(partidas);
        }
    }




