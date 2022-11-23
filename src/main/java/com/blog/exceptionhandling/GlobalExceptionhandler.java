package com.blog.exceptionhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.playload.AppResponse;

@RestControllerAdvice
public class GlobalExceptionhandler {
      
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<AppResponse> ResourcenotFoundException(ResourceNotFoundException e){
		String message= e.getMessage();
		
		AppResponse response= new AppResponse(message, false);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotValidException(MethodArgumentNotValidException e){
		
		Map<String, String> resp= new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach((error)->{
			
			String field = ((FieldError)error).getField();
			
		String defaultMessage = error.getDefaultMessage();
		
		resp.put(field, defaultMessage);
			
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(APIException.class)
	public ResponseEntity<AppResponse> InvalidException(APIException e){
		String message= e.getMessage();
		
		AppResponse response= new AppResponse(message, false);
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
