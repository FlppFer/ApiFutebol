//package meli.com.apifut.Controller;
//
//import meli.com.apifut.DTO.PartidaDTO;
//import meli.com.apifut.Service.PartidaService;
//import meli.com.apifut.Service.TimeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/partidas")
//public class PartidaController {
//
//    private final PartidaService partidaService;
//    private final TimeService timeService;
//
//    @Autowired
//    public PartidaController(PartidaService partidaService, TimeService timeService) {
//        this.partidaService = partidaService;
//        this.timeService = timeService;
//    }
//    @PostMapping("/criar")
//    public ResponseEntity<PartidaDTO> criarNovaPartida(@RequestBody PartidaDTO partidaDTO) {
//        PartidaDTO novaPartida = partidaService.criarNovaPartida(partidaDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novaPartida);
//    }
//
//    @PutMapping("/editarPartida")
//    public ResponseEntity<PartidaDTO> editarPartida(@PathVariable long id,@RequestBody PartidaDTO partidaDTO) {
//        PartidaDTO partidaEditada = partidaService.editarPartida(id, partidaDTO);
//        if (partidaEditada != null) {
//            return ResponseEntity.ok(partidaEditada);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//    @DeleteMapping("/deletarPartida")
//    public ResponseEntity<Void> deletarPartida(@PathVariable Long id) {
//        PartidaDTO partidaRemovida = partidaService.removerPartidaPorID(id);
//        if (partidaRemovida != null) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/listarPartidas")
//    public ResponseEntity<List<PartidaDTO>> listarPartidas() {
//        List<PartidaDTO> partidas = partidaService.listarTodasAsPartidas();
//        return ResponseEntity.ok(partidas);
//    }
//
//    @GetMapping("/{buscarPartidaPorId}")
//    public ResponseEntity<PartidaDTO> buscarPartidaPorId(@PathVariable Long id) {
//        PartidaDTO partida = partidaService.buscarPartidaPorId(id);
//        if (partida != null) {
//            return ResponseEntity.ok(partida);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
