package io.bramcode.movie.moviecategoryservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
note :
@ControllerAdvice : allows our class to be global interceptor of exception thrown by methods annotated by request mapping
@ExceptionHandler : declare that our  handleAllExceptionMethod will handle any exception of that type CustomException
 */
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleAllException(CustomException ex){
      CustomExceptionSchema customExceptionSchema = new CustomExceptionSchema(ex.getRootCause(),
              ex.getMessage(), ex.getDetails()
      );
        return new ResponseEntity<>(customExceptionSchema, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
