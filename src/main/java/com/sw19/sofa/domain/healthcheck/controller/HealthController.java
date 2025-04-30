package com.sw19.sofa.domain.healthcheck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw19.sofa.domain.healthcheck.api.HealthCheckApi;
import com.sw19.sofa.global.common.dto.BaseResponse;

@Controller
@RequestMapping("/health-check")
public class HealthController implements HealthCheckApi {

	@Override
	@GetMapping
	public ResponseEntity<String> healthCheck() {
		return BaseResponse.ok("Health Check Ok!");
	}
}
