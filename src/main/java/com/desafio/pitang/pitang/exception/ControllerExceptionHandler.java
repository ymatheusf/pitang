package com.desafio.pitang.pitang.exception;

import com.desafio.pitang.pitang.model.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity error(DataIntegrityViolationException dataIntegrityViolationException){
        ExceptionDTO exceptionDto = new ExceptionDTO("Login already exists", "500");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDto = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity threatGeneralRuntimeException(RuntimeException runtimeException){
        ExceptionDTO exceptionDTO = new ExceptionDTO(runtimeException.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }


}
