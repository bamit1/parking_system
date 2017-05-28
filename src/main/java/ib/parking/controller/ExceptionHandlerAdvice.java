package ib.parking.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorDto("Internal Server Error"), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> hadleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error(e.getMessage());

        String errMsg = e.getMessage();
        return new ResponseEntity<>(new ErrorDto(errMsg), HttpStatus.BAD_REQUEST);
    }

}
