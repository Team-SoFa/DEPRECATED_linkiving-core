package com.sw19.sofa.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AiMessageDto(String role, @Schema(description = "prompt") String content) {

}
