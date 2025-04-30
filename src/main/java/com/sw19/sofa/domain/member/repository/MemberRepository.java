package com.sw19.sofa.domain.member.repository;

import static com.sw19.sofa.domain.member.error.MemberErrorCode.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.error.exception.BusinessException;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findById(Long memberId);

	default Member findByIdOrElseThrow(Long userId) {
		return findById(userId).orElseThrow(() -> new BusinessException(NOT_FOUND_USER));
	}

	Optional<Member> findByEmail(String email);
}
