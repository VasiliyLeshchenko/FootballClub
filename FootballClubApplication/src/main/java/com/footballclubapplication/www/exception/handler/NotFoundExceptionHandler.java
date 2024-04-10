package com.footballclubapplication.www.exception.handler;

import com.footballclub.core.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, WebRequest request
    ) {
        logger.error(ex.getMessage(), ex);
        String body = "Your element could not be found";
        return handleExceptionInternal(ex, body ,new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
