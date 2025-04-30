package com.sw19.sofa.domain.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sw19.sofa.domain.ai.dto.request.ChatGptRequest;
import com.sw19.sofa.domain.ai.dto.response.ChatGptResponse;

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
		ChatGptRequest request = new ChatGptRequest(model, prompt, maxTokens);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);

		HttpEntity<ChatGptRequest> entity = new HttpEntity<>(request, headers);

		try {
			log.info("Sending request to OpenAI API");
			ChatGptResponse response = restTemplate.postForObject(apiUrl, entity, ChatGptResponse.class);
			log.info("Received response from OpenAI API");

			if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
				ChatGptResponse.Message message = response.getChoices().get(0).getMessage();
				if (message != null) {
					return message.getContent();
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
