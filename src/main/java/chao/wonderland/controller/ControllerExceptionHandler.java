package chao.wonderland.controller;


import chao.wonderland.dto.ErrorHeaderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<ErrorHeaderDTO> handlePersistenceException(
            PersistenceException ex, HttpServletRequest request) {
        var errorHeader = new ErrorHeaderDTO("400",
                ex != null ? ex.getMessage() : "",
                null);
        return new ResponseEntity<>(errorHeader, HttpStatus.BAD_REQUEST);
    }

}
