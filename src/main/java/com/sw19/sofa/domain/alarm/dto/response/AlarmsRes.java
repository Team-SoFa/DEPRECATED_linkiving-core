package com.sw19.sofa.domain.alarm.dto.response;

import java.util.List;

import com.sw19.sofa.domain.alarm.dto.AlarmDto;

public record AlarmsRes(
	List<AlarmDto> alarms
) {
}
