package com.api.exception;

import com.api.payload.errorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotfFoundException.class)
    public ResponseEntity <errorDto> resourceNotFound(
            ResourceNotfFoundException r , WebRequest request

    ){
        errorDto error = new errorDto(r.getMessage() ,new Date() ,request.getDescription(false));
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);

}
@ExceptionHandler(Exception.class)
    public ResponseEntity <String> handleAllExceptions(
            Exception e
){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);

}


}
