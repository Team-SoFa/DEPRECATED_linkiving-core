package com.sw19.sofa.domain.ai.dto.response;

import java.util.List;

import com.sw19.sofa.domain.ai.dto.ChoiceDto;

public record GPTRes(
	String id,
	String object,
	long created,
	String model,
	List<ChoiceDto> choices
) {
}
