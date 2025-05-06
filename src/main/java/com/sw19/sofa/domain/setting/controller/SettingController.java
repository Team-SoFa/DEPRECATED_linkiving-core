package com.sw19.sofa.domain.setting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.domain.setting.api.SettingApi;
import com.sw19.sofa.domain.setting.dto.request.ToggleAlarmReq;
import com.sw19.sofa.domain.setting.dto.response.SettingRes;
import com.sw19.sofa.domain.setting.service.SettingService;
import com.sw19.sofa.global.common.dto.BaseResponse;
import com.sw19.sofa.security.jwt.AuthMember;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController implements SettingApi {
	private final SettingService settingService;

	@Override
	@GetMapping
	public ResponseEntity<SettingRes> getSetting(@AuthMember Member member) {
		SettingRes res = settingService.getMemberSetting(member);
		return BaseResponse.ok(res);
	}

	@Override
	@PatchMapping("/alarm")
	public ResponseEntity<SettingRes> toggleAlarm(
		@AuthMember Member member,
		@Valid @RequestBody ToggleAlarmReq request
	) {
		SettingRes res = settingService.toggleAlarm(member, request.alarmType());
		return BaseResponse.ok(res);
	}
}
