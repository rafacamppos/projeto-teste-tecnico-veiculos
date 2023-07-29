package com.aditamento.veiculos.adapter.controller.server;

import com.aditamento.veiculos.adapter.controller.entity.ErrorMessage;
import com.aditamento.veiculos.domain.exceptions.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ErrorHandlerController{

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest webRequest) {
        ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build();
        return new ResponseEntity<>(ErrorMessage.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handlergumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        return new ResponseEntity<>(ErrorMessage.builder().message(String.valueOf(errors)).status(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
    }

}
