package chao.wonderland.controller;


import chao.wonderland.dto.ErrorHeaderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<ErrorHeaderDTO> handlePersistenceException(
            PersistenceException ex, HttpServletRequest request) {
        var errorHeader = new ErrorHeaderDTO("400",
                ex.getMessage(),
                null);
        return new ResponseEntity<>(errorHeader, HttpStatus.BAD_REQUEST);
    }

    @Override
    public final ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errorHeader =  new ErrorHeaderDTO("400", "mal-formatted value found",
                        null );
        return new ResponseEntity<>(errorHeader, HttpStatus.BAD_REQUEST);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errorHeader =
                new ErrorHeaderDTO("400", Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), null);
        return new ResponseEntity<>(errorHeader, HttpStatus.BAD_REQUEST);
    }

}
