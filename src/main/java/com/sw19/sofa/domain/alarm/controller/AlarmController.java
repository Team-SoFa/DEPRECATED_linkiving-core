package com.sw19.sofa.domain.alarm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw19.sofa.domain.alarm.api.AlarmApi;
import com.sw19.sofa.domain.alarm.dto.response.AlarmsRes;
import com.sw19.sofa.domain.alarm.service.AlarmService;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.common.dto.BaseResponse;
import com.sw19.sofa.security.jwt.AuthMember;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController implements AlarmApi {
	private final AlarmService alarmService;

	@Override
	@GetMapping
	public ResponseEntity<AlarmsRes> getAlarmList(@AuthMember Member member) {
		AlarmsRes res = alarmService.getAlarmList(member);
		return BaseResponse.ok(res);
	}

	@Override
	@PostMapping("/{id}")
	public ResponseEntity<String> readAlarm(@PathVariable String id) {
		alarmService.readAlarm(id);
		return BaseResponse.ok("읽음 처리 성공");
	}
}
