package edu.myschool.admin.exception;

import edu.myschool.admin.model.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({AlreadyExistingException.class, PersonNotFoundException.class, BindException.class})
    public ResponseEntity<Error> handleValidationExceptions(
            Exception ex) {
        String message = ex.getMessage();
        if(ex instanceof BindException){
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : ((BindException) ex).getAllErrors()) {
                sb.append(error.getDefaultMessage() + " ");
            }
            message = sb.toString();
        }
        return new ResponseEntity<>(new Error(message), HttpStatus.BAD_REQUEST);
    }
}
