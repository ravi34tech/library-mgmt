package com.ravi.library.error;

import com.ravi.library.bookmgmt.exception.BookDetailsNotFoundException;
import com.ravi.library.bookmgmt.exception.BookMgmtCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LibraryManagementGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryManagementGlobalExceptionHandler.class);

    @ExceptionHandler(BookMgmtCommonException.class)
    public ResponseEntity<String> bookManagementCommonException(BookMgmtCommonException exception){
        LOGGER.error("Error: {}",exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookDetailsNotFoundException.class)
    public ResponseEntity<String> bookDetailsNotFoundException(BookDetailsNotFoundException exception){
        LOGGER.error("Book Details Not Found Error: {}",exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> libraryManagementCommonException(Exception exception){
        LOGGER.error("Exception : ",exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
