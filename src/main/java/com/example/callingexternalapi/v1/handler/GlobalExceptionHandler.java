package com.example.callingexternalapi.v1.handler;

import com.example.callingexternalapi.v1.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
     public ResponseEntity<ExceptionDetails> handleMethodNotSupported(HttpRequestMethodNotSupportedException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Error")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .title("Method not supported")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Something is missing")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Something in body is wrong")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(
            NullPointerException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Something is wrong")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Something is wrong")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<?> handleUnexpectedTypeException(
            UnexpectedTypeException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .title("Something is wrong")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(
            IllegalArgumentException exception){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : Check the documentation")
                .detail(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .title("Invalid Argument")
                .developerMessage(exception.getClass().getName())
                .build(), HttpStatus.NOT_ACCEPTABLE);
    }

}
