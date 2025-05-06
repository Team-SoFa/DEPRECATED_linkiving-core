package com.sw19.sofa.domain.linkcard.dto.response;

import java.util.List;

import com.sw19.sofa.domain.linkcard.dto.LinkCardTagDto;

public record LinkCardTagsRes(
	List<LinkCardTagDto> tags
) {
}
