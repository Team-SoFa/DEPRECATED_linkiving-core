package com.sw19.sofa.domain.setting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.domain.setting.dto.response.SettingRes;
import com.sw19.sofa.domain.setting.entity.Setting;
import com.sw19.sofa.domain.setting.enums.AlarmType;
import com.sw19.sofa.domain.setting.error.SettingErrorCode;
import com.sw19.sofa.domain.setting.repository.SettingRepository;
import com.sw19.sofa.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SettingService {
	private final SettingRepository settingRepository;

	@Transactional
	public void setNewUser(Member member) {
		Setting setting = Setting.builder()
			.member(member)
			.is_recommend(true)
			.is_remind(true)
			.is_notice(true)
			.build();

		settingRepository.save(setting);
	}

	public SettingRes getMemberSetting(Member member) {
		Setting setting = settingRepository.findByMemberOrThrow(member);
		return SettingRes.from(setting);
	}

	@Transactional
	public SettingRes toggleAlarm(Member member, AlarmType alarmType) {
		Setting setting = settingRepository.findByMemberOrThrow(member);

		switch (alarmType) {
			case REMIND -> setting.toggleRemindAlarm();
			case RECOMMEND -> setting.toggleRecommendAlarm();
			case NOTICE -> setting.toggleNoticeAlarm();
			default -> throw new BusinessException(SettingErrorCode.INVALID_ALARM_TYPE);
		}

		Setting save = settingRepository.save(setting);

		return SettingRes.from(save);
	}
}
