package com.Tambola.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(NoDataFoundExceptionclass.class)
	public ResponseEntity<ErrorDetails> noDataFoundExceptionHandler(NoDataFoundExceptionclass notFound, WebRequest web_Request)
	{
		ErrorDetails errDetails = new ErrorDetails(LocalDateTime.now(), notFound.getMessage(),
				web_Request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errDetails,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> handleMyExceptions(NoHandlerFoundException noHandlerFoundException,
			WebRequest web_Request) {
		ErrorDetails errDetails = new ErrorDetails(LocalDateTime.now(), noHandlerFoundException.getMessage(),
				web_Request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errDetails, HttpStatus.BAD_REQUEST);
	}
}
