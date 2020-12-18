package com.dp.employee.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dp.employee.controller.EmployeeController;


@ControllerAdvice(assignableTypes = EmployeeController.class)
@RestController
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exr = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exr, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleEmpNotFoundException(EmployeeNotFoundException ex, WebRequest request){
		
		ExceptionResponse exr = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exr, HttpStatus.NOT_FOUND);
	}
}
