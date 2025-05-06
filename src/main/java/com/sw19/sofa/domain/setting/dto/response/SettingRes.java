package com.sw19.sofa.domain.setting.dto.response;

import com.sw19.sofa.domain.setting.entity.Setting;

public record SettingRes(
	String id,
	boolean isRemindAlarm,
	boolean isRecommendAlarm,
	boolean isNoticeAlarm
) {
	public static SettingRes from(Setting setting) {
		return new SettingRes(
			setting.getEncryptId(),
			setting.getIs_remind(),
			setting.getIs_recommend(),
			setting.getIs_notice()
		);
	}
}
