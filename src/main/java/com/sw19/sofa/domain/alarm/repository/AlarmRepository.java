package com.sw19.sofa.domain.alarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.alarm.entity.Alarm;
import com.sw19.sofa.domain.member.entity.Member;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
	List<Alarm> findAllByMember(Member member);
}
