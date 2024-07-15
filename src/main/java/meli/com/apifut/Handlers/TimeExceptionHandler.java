package meli.com.apifut.Handlers;

import meli.com.apifut.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TimeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CamposObrigatoriosException.class)
    public ResponseEntity<String> camposObrigatoriosHandler(CamposObrigatoriosException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(DataNoFuturoException.class)
    public ResponseEntity<String> dataInvalidaHandler(DataNoFuturoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(NomeTimeInvalidoException.class)
    public ResponseEntity<String> nomeTimeInvalidoHandler(NomeTimeInvalidoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(SiglaInvalidaException.class)
    public ResponseEntity<String> siglaInvalidaHandler(SiglaInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(TimeDuplicadoException.class)
    public ResponseEntity<String> timeDuplicadoHandler(TimeDuplicadoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(TimeNaoEncontradoException.class)
    public ResponseEntity<String> timeNaoEncontradoHandler(TimeNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DataAposPartidaException.class)
    public ResponseEntity<String> dataAposPartidaHandler(DataAposPartidaException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
