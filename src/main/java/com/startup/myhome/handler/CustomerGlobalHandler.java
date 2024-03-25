package com.startup.myhome.handler;

import com.startup.myhome.dto.ErrorDetails;
import com.startup.myhome.exception.NotDataFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.management.InstanceNotFoundException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomerGlobalHandler {

    private static final Logger logger
            = LoggerFactory.getLogger(CustomerGlobalHandler.class);

    @ExceptionHandler(NotDataFoundException.class)
    public ResponseEntity<ErrorDetails> customerNotFound(NotDataFoundException ex, WebRequest webRequest) {
        logger.error("Data not found: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDetails> customerNull(MissingServletRequestParameterException ex, WebRequest webRequest) {
        logger.error("Missing request parameter: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message("Missing request parameter")
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<ErrorDetails> customerNull(InstanceNotFoundException ex, WebRequest webRequest) {
        logger.error("Instance not found: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message("Instance not found")
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> customerNull(IllegalArgumentException ex, WebRequest webRequest) {
        logger.error("Illegal argument: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message("Illegal argument")
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> customerNull(MethodArgumentNotValidException ex, WebRequest webRequest) {
        logger.error("Method argument not valid: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getBindingResult().getFieldError().getField() + " is required")
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> customerNull(RuntimeException ex, WebRequest webRequest) {
        logger.error("Runtime exception: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> customerException(Exception ex, WebRequest webRequest) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        var errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errorDetail(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }
}
