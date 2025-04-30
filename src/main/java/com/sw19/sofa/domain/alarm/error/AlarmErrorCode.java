package com.sw19.sofa.domain.alarm.error;

import org.springframework.http.HttpStatus;

import com.sw19.sofa.global.error.code.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmErrorCode implements ErrorCode {
	NOT_FOUND_ALARM(HttpStatus.NOT_FOUND, "Al-001", "알림 정보를 찾지 못했습니다.");
	private final HttpStatus status;
	private final String code;
	private final String message;
}
