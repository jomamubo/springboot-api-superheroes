package com.w2m.springboot.api.superheroes.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ExceptionController extends ResponseEntityExceptionHandler{
  
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<MensajeError> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		MensajeError message = new MensajeError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
	    return new ResponseEntity<MensajeError>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MensajeError> globalExceptionHandler(Exception ex, WebRequest request) {
		MensajeError message = new MensajeError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
	    
		return new ResponseEntity<MensajeError>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}