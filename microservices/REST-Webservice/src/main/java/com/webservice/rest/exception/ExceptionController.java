package com.webservice.rest.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handle(Exception ex, WebRequest request)  {
		NotFoundException notFoundException=
				new NotFoundException(new Date(),ex.getMessage(),request.getDescription(false));
	    return new ResponseEntity(notFoundException,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> UserNotFoundException(UserNotFoundException ex, WebRequest request)  {
		NotFoundException notFoundException=
				new NotFoundException(new Date(),request.getDescription(false),ex.getMessage());
	    return new ResponseEntity(notFoundException,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		NotFoundException notFoundException=
				new NotFoundException(new Date(),ex.getBindingResult().getFieldError().getField(),"Validation error");
	    return new ResponseEntity(notFoundException,HttpStatus.BAD_REQUEST);
	}
	

}
