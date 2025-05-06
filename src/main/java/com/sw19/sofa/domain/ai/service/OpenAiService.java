package com.sw19.sofa.domain.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sw19.sofa.domain.ai.dto.MessageDto;
import com.sw19.sofa.domain.ai.dto.request.GptReq;
import com.sw19.sofa.domain.ai.dto.response.GPTRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OpenAiService {

	private final RestTemplate restTemplate;
	private final String apiUrl;
	private final String apiKey;
	private final String model;

	public OpenAiService(RestTemplate restTemplate,
		@Value("${openai.api.url}") String apiUrl,
		@Value("${openai.api.key}") String apiKey,
		@Value("${openai.model}") String model) {
		this.restTemplate = restTemplate;
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
		this.model = model;
	}

	public String sendPrompt(String prompt, int maxTokens) {
		log.info("Creating ChatGPT request with max tokens: {}", maxTokens);
		GptReq request = new GptReq(model, prompt, maxTokens);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);

		HttpEntity<GptReq> entity = new HttpEntity<>(request, headers);

		try {
			log.info("Sending request to OpenAI API");
			GPTRes response = restTemplate.postForObject(apiUrl, entity, GPTRes.class);
			log.info("Received response from OpenAI API");

			if (response != null && response.choices() != null && !response.choices().isEmpty()) {
				MessageDto message = response.choices().get(0).message();
				if (message != null) {
					return message.content();
				}
			}
		} catch (Exception e) {
			log.error("Error calling OpenAI API: {}", e.getMessage(), e);
			throw new RuntimeException("OpenAI API 호출 중 오류 발생", e);
		}
		log.warn("No valid response received from OpenAI API");
		return "OpenAI API로부터 유효한 응답을 받지 못했습니다.";
	}
}
