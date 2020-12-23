package com.snippets.webspoons.globals;

import java.util.HashMap;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.snippets.webspoons.utilities.ResponseCodeEnum;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> validationExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> responseBody = new HashMap<>();
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.BAD_REQUEST.getResponseCode());
		errors.put("description", ResponseCodeEnum.BAD_REQUEST.getDescription());
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			responseBody.put(fieldName, errorMessage);
		});
		errors.put("message", responseBody);
		return errors;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> RunTimeException(RuntimeException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getResponseCode());
		errors.put("description", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getDescription());
		errors.put("message", ex.getClass());

		ex.printStackTrace();

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<Map<String, Object>> handleConversionEception(HttpMessageConversionException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getResponseCode());
		errors.put("description", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getDescription());
		errors.put("message", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getDescription());

		ex.printStackTrace();

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.SYSTEM_MALFUNCTION.getResponseCode());
		errors.put("description", ResponseCodeEnum.SYSTEM_MALFUNCTION.getDescription());
		errors.put("message", ResponseCodeEnum.SYSTEM_MALFUNCTION.getDescription());

		ex.printStackTrace();

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String, Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.METHOD_NOT_ALLOWED.getResponseCode());
		errors.put("description", ResponseCodeEnum.METHOD_NOT_ALLOWED.getDescription());
		errors.put("message", ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Map<String, Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.UNSUPPORTED_MEDIA_TYPE.getResponseCode());
		errors.put("description", ResponseCodeEnum.UNSUPPORTED_MEDIA_TYPE.getDescription());
		errors.put("message", ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
	public Map<String, Object> handleHttpClientErrorException(HttpClientErrorException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.BAD_REQUEST.getResponseCode());
		errors.put("description", ResponseCodeEnum.BAD_REQUEST.getDescription());
		errors.put("message", ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@org.springframework.web.bind.annotation.ExceptionHandler(ServiceUnavailableException.class)
	public Map<String, Object> handleServiceUnavailableException(ServiceUnavailableException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResponseCode());
		errors.put("description", ResponseCodeEnum.INTERNAL_SERVER_ERROR.getDescription());
		errors.put("message", ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceAccessException.class)
	public Map<String, Object> handleResourceAccessException(ResourceAccessException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResponseCode());
		errors.put("description", ResponseCodeEnum.INTERNAL_SERVER_ERROR.getDescription());
		errors.put("message", ex.getMessage());

		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.BAD_REQUEST.getResponseCode());
		errors.put("description", ResponseCodeEnum.BAD_REQUEST.getDescription());
		errors.put("message", ex.getHttpInputMessage());

		return errors;
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
	public Map<String, Object> handleHttpServerErrorException(HttpServerErrorException ex) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("responseCode", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getResponseCode());
		errors.put("description", ResponseCodeEnum.GENERAL_OR_FATAL_ERROR.getDescription());
		errors.put("message", ex.getMessage());

		return errors;
	}
}
