package com.sw19.sofa.domain.linkcard.repository;

import java.util.Map;

import com.sw19.sofa.domain.linkcard.entity.LinkCardTag;
import com.sw19.sofa.domain.member.entity.Member;

public interface LinkCardTagCustomRepository {
	LinkCardTag findMostTagIdByMember(Member member);

	Map<LinkCardTag, Long> findTagStatisticsByMember(Member member);
}
