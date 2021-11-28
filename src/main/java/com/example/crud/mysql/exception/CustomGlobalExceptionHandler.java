package com.example.crud.mysql.exception;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

	@ExceptionHandler(MscException.class)
	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex , WebRequest request , HttpServletRequest httprequest){
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setError(ex.getMessage());
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
		errors.setPath(httprequest.getRequestURI());
		return new ResponseEntity<CustomErrorResponse>(HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(MscObjectSaveException.class)
	public ResponseEntity<CustomErrorResponse> objectSaveException(MscObjectSaveException ex , WebRequest request , HttpServletRequest httprequest){
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setError(ex.getMessage());
		errors.setTimestamp(LocalDateTime.now());
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.setPath(httprequest.getRequestURI());
		return new ResponseEntity<CustomErrorResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	 
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

	
}
