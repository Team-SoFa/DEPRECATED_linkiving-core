package com.sw19.sofa.global.error.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.sw19.sofa.global.error.code.ErrorCode;

import jakarta.validation.ConstraintViolation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
	private HttpStatus status;
	private String code;
	private String message;
	private List<CustomFieldError> errors;

	private ErrorResponse(final ErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = new ArrayList<>();
	}

	private ErrorResponse(final ErrorCode errorCode, final List<CustomFieldError> errors) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = errors;
	}

	public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
		return new ErrorResponse(errorCode, CustomFieldError.of(bindingResult));
	}

	public static ErrorResponse of(final ErrorCode errorCode, final Set<ConstraintViolation<?>> constraintViolations) {
		return new ErrorResponse(errorCode, CustomFieldError.of(constraintViolations));
	}

	public static ErrorResponse of(final ErrorCode errorCode, final String message) {
		return new ErrorResponse(errorCode, CustomFieldError.of("", "", message));
	}

	public static ErrorResponse of(final ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
	}

	public static ErrorResponse of(final ErrorCode code, final List<CustomFieldError> errors) {
		return new ErrorResponse(code, errors);
	}

	public static ErrorResponse of(final ErrorCode code, final MethodArgumentTypeMismatchException error) {
		return new ErrorResponse(code, CustomFieldError.of(error));
	}

	public static ErrorResponse of(final ErrorCode code, final MissingServletRequestParameterException error) {
		return new ErrorResponse(code, CustomFieldError.of(error));
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class CustomFieldError {
		private String field;
		private String value;
		private String reason;

		public CustomFieldError(String field, String value, String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}

		public static List<CustomFieldError> of(final String field, final String value, final String reason) {
			List<CustomFieldError> customFieldErrors = new ArrayList<>();
			customFieldErrors.add(new CustomFieldError(field, value, reason));
			return customFieldErrors;
		}

		public static List<CustomFieldError> of(final BindingResult bindingResult) {
			final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
			return fieldErrors.stream()
				.map(error -> new CustomFieldError(
					error.getField(),
					error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
					error.getDefaultMessage()
				))
				.collect(Collectors.toList());
		}

		public static List<CustomFieldError> of(final Set<ConstraintViolation<?>> constraintViolations) {
			List<ConstraintViolation<?>> lists = new ArrayList<>(constraintViolations);
			return lists.stream()
				.map(error -> new CustomFieldError(
					error.getPropertyPath().toString(),
					"",
					error.getMessage()
				))
				.collect(Collectors.toList());
		}

		public static List<CustomFieldError> of(final MethodArgumentTypeMismatchException error) {
			final String value = error.getValue() == null ? "" : error.getValue().toString();
			return CustomFieldError.of(error.getName(), value, error.getErrorCode());
		}

		public static List<CustomFieldError> of(final MissingServletRequestParameterException error) {
			return CustomFieldError.of(error.getParameterName(), "", error.getMessage());
		}
	}
}
