package com.sw19.sofa.domain.linkcard.dto;

import com.sw19.sofa.domain.linkcard.entity.LinkCardTag;
import com.sw19.sofa.domain.linkcard.enums.TagType;
import com.sw19.sofa.global.util.EncryptionUtil;

import io.swagger.v3.oas.annotations.media.Schema;

public record LinkCardTagSimpleDto(
	@Schema(description = "태그 아이디") String id,
	@Schema(description = "태그 속성", example = "AI/CUSTOM") TagType tagType
) {
	public LinkCardTagSimpleDto(LinkCardTag linkCardTag) {
		this(EncryptionUtil.encrypt(linkCardTag.getTagId()), linkCardTag.getTagType());
	}

	public Long decryptionId() {
		return EncryptionUtil.decrypt(id);
	}
}
