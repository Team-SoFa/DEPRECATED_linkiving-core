package com.sw19.sofa.domain.alarm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sw19.sofa.domain.alarm.dto.AlarmDto;
import com.sw19.sofa.domain.alarm.dto.response.AlarmsRes;
import com.sw19.sofa.domain.alarm.entity.Alarm;
import com.sw19.sofa.domain.alarm.enums.AlarmType;
import com.sw19.sofa.domain.alarm.error.AlarmErrorCode;
import com.sw19.sofa.domain.alarm.repository.AlarmRepository;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.error.exception.BusinessException;
import com.sw19.sofa.global.util.EncryptionUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {
	private final AlarmRepository alarmRepository;

	@Transactional
	public void addRemindAlarm(Member member, String content) {
		Alarm alarm = Alarm.builder()
			.member(member)
			.content(content)
			.type(AlarmType.REMIND)
			.isRead(Boolean.FALSE)
			.build();

		alarmRepository.save(alarm);
	}

	@Transactional
	public void deleteExpiredAlarm() {
		List<Alarm> alarmList = alarmRepository.findAll();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime threeMonthsAgo = currentDateTime.minusMonths(3);

		List<Alarm> deleteList = alarmList.stream().filter(
			alarm -> alarm.getCreatedAt().isBefore(threeMonthsAgo)
		).toList();
		alarmRepository.deleteAll(deleteList);
	}

	public AlarmsRes getAlarmList(Member member) {
		List<Alarm> alarmList = alarmRepository.findAllByMember(member);
		List<AlarmDto> alarmDtos = alarmList.stream().map(AlarmDto::new).toList();

		return new AlarmsRes(alarmDtos);
	}

	@Transactional
	public void readAlarm(String encryptId) {
		Long id = EncryptionUtil.decrypt(encryptId);
		Alarm alarm = alarmRepository.findById(id)
			.orElseThrow(() -> new BusinessException(AlarmErrorCode.NOT_FOUND_ALARM));
		alarm.setRead();
		alarmRepository.save(alarm);
	}
}
