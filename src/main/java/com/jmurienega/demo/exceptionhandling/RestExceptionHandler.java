package com.jmurienega.demo.exceptionhandling;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(value = {CustomUserException.class})
    public ResponseEntity<Object> handleUserNotFoundException(CustomUserException exception, WebRequest request){
        return buildResponseEntity(new ApiError(exception));
    }
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 return buildResponseEntity(new ApiError(4,ex.getMessage()));
	}
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
		
		List<String> errors = ex.getBindingResult()
			        .getFieldErrors()
			        .stream()
			        .map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			    
        return buildResponseEntity(new ApiError(3,errors.toString()));
    }
}
