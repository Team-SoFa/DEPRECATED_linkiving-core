package com.sw19.sofa.domain.ai.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.sw19.sofa.global.common.dto.AiMessageDto;

import lombok.Data;

@Data
public class ChatGptRequest {
	private String model;
	private List<AiMessageDto> messages;
	private int maxTokens;

	public ChatGptRequest(String model, String prompt, int maxTokens) {
		this.model = model;
		this.maxTokens = maxTokens;
		this.messages = new ArrayList<>();
		this.messages.add(new AiMessageDto("user", prompt));
	}
}
