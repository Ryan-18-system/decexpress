package br.edu.ifpb.decexpress.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> error404() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMensagem("Recurso não encontrado.");
        errorDTO.setDetalhes("O recurso que você está procurando não foi encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(value = {RuntimeException.class, PersistenceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMensagem("Ocorreu um erro interno no servidor.");
        errorDTO.setDetalhes(ex.getMessage()); // Você pode personalizar os detalhes da exceção aqui

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
