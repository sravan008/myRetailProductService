package com.target.myretail.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleDefaultException(Exception exception) {
        log.error("Exception processing request", exception);
        String errMsg = "{ \"message\": \"Internal server error has occurred\"}";
        return new ResponseEntity<String>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductDataNotFoundException.class)
    public ResponseEntity<String> handleProductDataNotFoundException(Exception ex) {
        log.error("Product Not Found", ex);
        String errMsg = ex.getMessage();
        return new ResponseEntity<String>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductServicesException.class)
    public ResponseEntity<String> handleProductServicesException(Exception ex) {
        log.error("Exception occurred during the create or update operation ", ex);
        String errMsg = ex.getMessage();
        return new ResponseEntity<String>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
