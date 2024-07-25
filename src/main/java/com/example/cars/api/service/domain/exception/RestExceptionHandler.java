package com.example.cars.api.service.domain.exception;

import com.example.cars.api.service.domain.exception.utils.CustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({CarNotFoundException.class, UsernameNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<CustomExceptionResponse> errorNotFound(CarNotFoundException ex) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<CustomExceptionResponse> errorBadRequest(IllegalArgumentException ex){
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
