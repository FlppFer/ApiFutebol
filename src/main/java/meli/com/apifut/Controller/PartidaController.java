package meli.com.apifut.Controller;

import meli.com.apifut.DTO.*;
import meli.com.apifut.Service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {
    @Autowired
    PartidaService partidaService;

    @PostMapping("/criarPartida")
    public ResponseEntity<?> criarNovaPartida(@RequestBody PartidaDTO partidaDTO) {
            partidaService.criarNovaPartida(partidaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("A partida foi registrada com sucesso!");

    }

    @DeleteMapping("/deletarPartidaPorID")
    public ResponseEntity<?> deletarPartida(@RequestParam Long id) {
            partidaService.removerPartidaPorID(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/editarPartidaPorId")
    public ResponseEntity<?> editarPartida(@RequestParam Long id, @RequestBody PartidaDTO partidaDTO) {
            partidaService.editarPartida(id, partidaDTO);
            return ResponseEntity.status(HttpStatus.OK).body("A partida entre " + partidaDTO.getTimeCasa() + " e "+ partidaDTO.getTimeVisitante() + " foi editada com sucesso!");
    }

    @GetMapping("/buscarPartidaPorId")
    public ResponseEntity<?> buscarPartidaPorId(@RequestParam Long id) {
            PartidaDTO partidaDTO = partidaService.buscarPartidaPorId(id);
            return ResponseEntity.ok(partidaDTO);
    }

    @GetMapping("/listarPartidas/")
    public ResponseEntity<Page<PartidaDTO>> listarPartidas(
            @RequestParam(required = false) Long timeId,
            @RequestParam(required = false) Long estadioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ){
        Page<PartidaDTO> partidas = partidaService.listarPartidas(timeId, estadioId,
                PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
        return ResponseEntity.ok(partidas);
        }

    @GetMapping("/historicoGeral")
    public ResponseEntity<?> getHistoricoGeral(@Param("id") Long timeId) {
        HistoricoGeralDTO historicoGeralDTO = partidaService.gerarHistoricoGeral(timeId);
        return ResponseEntity.ok(historicoGeralDTO);
    }
    }
