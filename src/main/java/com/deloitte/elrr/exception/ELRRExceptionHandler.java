/**
 * 
 */
package com.deloitte.elrr.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author mnelakurti
 *
 */
@ControllerAdvice
public class ELRRExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> recordNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ELRRErrorDetails errorDetails = new ELRRErrorDetails(new Date(), ex.getMessage(), request.getDescription(false),
				null);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ELRRErrorDetails errorDetails = new ELRRErrorDetails(new Date(), "Validation Failed", request.getDescription(false),
				details);
		return new ResponseEntity<>(errorDetails, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> elrrExcpetionHandler(Exception ex, WebRequest request) {
		ELRRErrorDetails errorDetails = new ELRRErrorDetails(new Date(), ex.getMessage(), request.getDescription(false),
				null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}