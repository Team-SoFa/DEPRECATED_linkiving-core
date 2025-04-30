package com.sw19.sofa.global.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.sw19.sofa.global.error.code.CommonErrorCode;
import com.sw19.sofa.global.error.code.ErrorCode;
import com.sw19.sofa.global.error.dto.ErrorResponse;
import com.sw19.sofa.global.error.exception.BusinessException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException error) {
		log.error("MethodArgumentNotValidException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, error.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> handleBindException(BindException error) {
		log.error("BindException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE, error.getBindingResult());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException error) {
		log.error("ConstraintViolationException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE,
			error.getConstraintViolations());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException error) {
		log.error("HttpRequestMethodNotSupportedException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException error) {
		log.error("MethodArgumentTypeMismatchException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.TYPE_MISMATCH, error);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ErrorResponse> handleException(MissingServletRequestParameterException error) {
		log.error("MissingServletRequestParameterException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.MISSING_REQUEST_PARAMS, error);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException error) {
		log.error("NullPointerException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.NULL_POINT, error.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException error) {
		log.error("BusinessException", error);
		ErrorCode errorCode = error.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);
		return new ResponseEntity<>(response, errorCode.getStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception error) {
		log.error("Internal Server Error", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.INTERNAL_SERVER_ERROR, error.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException error) {
		log.error("NoResourceFoundException", error);
		final ErrorResponse response = ErrorResponse.of(CommonErrorCode.RESOURCE_NOT_FOUND, error.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}

