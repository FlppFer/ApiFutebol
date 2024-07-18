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

    @ExceptionHandler(ConflitoDeDadosException.class)
    public ResponseEntity<String> dataConflitoHandler(ConflitoDeDadosException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<String> dadoInvalidoHandler(DadosInvalidosException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }

    @ExceptionHandler(EntidadeDuplicadaException.class)
    public ResponseEntity<String> timeDuplicadoHandler(EntidadeDuplicadaException exception) {
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

}
