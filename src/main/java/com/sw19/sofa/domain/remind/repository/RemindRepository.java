package com.sw19.sofa.domain.remind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.linkcard.entity.LinkCard;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.domain.remind.entity.Remind;

@Repository
public interface RemindRepository extends JpaRepository<Remind, Long>, RemindCustomRepository {
	List<Remind> findAllByMember(Member member);

	boolean existsByMember(Member member);

	boolean existsByLinkCardAndMember(LinkCard linkCard, Member member);

	void deleteByLinkCardAndMember(LinkCard linkCard, Member member);
}
