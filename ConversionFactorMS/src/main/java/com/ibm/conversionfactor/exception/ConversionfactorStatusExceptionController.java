package com.ibm.conversionfactor.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ConversionfactorStatusExceptionController {

	@ExceptionHandler(value = ConversionFactorException.class)
	   public ResponseEntity<Object> handleexception(ConversionFactorException exception) {
		 return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
	   }



}
