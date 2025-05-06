package com.sw19.sofa.domain.ai.dto.request;

import com.sw19.sofa.global.common.dto.AiMessageDto;

import java.util.ArrayList;
import java.util.List;

public record GptReq(String model, List<AiMessageDto> messages, int maxToken)
{
    public GptReq(String model, String prompt, int maxToken) {
        this(model, new ArrayList<>(), maxToken);
        this.messages.add(new AiMessageDto("user", prompt));
    }
}
