package com.app.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.dto.ApiResponse;
import com.app.empexception.EmployeeException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(
			EmployeeException e) {
		System.out.println("in res not found " + e);
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handleAnyException(RuntimeException e) {
		e.printStackTrace();
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse handleAllException(Exception e) {
		System.out.println(e);
		e.printStackTrace();
		return new ApiResponse(e.getMessage());
	}

}
