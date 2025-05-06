package com.sw19.sofa.domain.linkcard.dto.request;

import java.util.List;

import com.sw19.sofa.domain.linkcard.dto.LinkCardTagSimpleDto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LinkCardTagListReq(
	@Schema(description = "태그 아이디 및 타입 목록") List<LinkCardTagSimpleDto> tagList
) {
}
