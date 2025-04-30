package com.sw19.sofa.domain.remind.dto;

import java.util.List;

import com.sw19.sofa.domain.linkcard.entity.LinkCard;
import com.sw19.sofa.domain.member.entity.Member;

public record MemberUnUsedLinkCardListDto(
	Member member,
	List<LinkCard> linkCardList
) {
}
