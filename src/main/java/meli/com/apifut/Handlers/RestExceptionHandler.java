package meli.com.apifut.Handlers;

import meli.com.apifut.Exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CamposObrigatoriosException.class)
    public ResponseEntity<String> camposObrigatoriosHandler(CamposObrigatoriosException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(DataConflitoException.class)
    public ResponseEntity<String> dataConflitoHandler(DataConflitoException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(NomeTimeInvalidoException.class)
    public ResponseEntity<String> nomeTimeInvalidoHandler(NomeTimeInvalidoException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(SiglaInvalidaException.class)
    public ResponseEntity<String> siglaInvalidaHandler(SiglaInvalidaException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(TimeDuplicadoException.class)
    public ResponseEntity<String> timeDuplicadoHandler(TimeDuplicadoException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<String> timeNaoEncontradoHandler(EntidadeNaoEncontradaException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
    @ExceptionHandler(TimeInativoException.class)
    public ResponseEntity<String> timeInativoHandler(TimeInativoException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(EstadioNaoEncontradoException.class)
    public ResponseEntity<String> estadioNaoEncontradoHandler(EstadioNaoEncontradoException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(GolsInvalidosException.class)
    public ResponseEntity<String> golsInvalidosHandler(GolsInvalidosException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

}
