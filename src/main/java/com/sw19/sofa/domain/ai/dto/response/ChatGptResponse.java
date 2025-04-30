package com.sw19.sofa.domain.ai.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptResponse {
	private String id;
	private String object;
	private long created;
	private String model;
	private List<Choice> choices;

	@Data
	public static class Choice {
		private int index;
		private Message message;
		private String finishReason;
	}

	@Data
	public static class Message {
		private String role;
		private String content;
	}
}
