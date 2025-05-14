package com.sw19.sofa.domain.ai.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.sw19.sofa.domain.ai.dto.AiMessageDto;

public record GptReq(String model, List<AiMessageDto> messages, int maxToken) {
	public GptReq(String model, String prompt, int maxToken) {
		this(model, new ArrayList<>(), maxToken);
		this.messages.add(new AiMessageDto("user", prompt));
	}
}
