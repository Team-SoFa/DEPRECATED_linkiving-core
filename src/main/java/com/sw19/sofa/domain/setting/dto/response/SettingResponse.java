package com.sw19.sofa.domain.setting.dto.response;

import com.sw19.sofa.domain.setting.entity.Setting;

public record SettingResponse(
	String encryptedId,
	boolean isRemindAlarm,
	boolean isRecommendAlarm,
	boolean isNoticeAlarm
) {
	public static SettingResponse from(Setting setting) {
		return new SettingResponse(
			setting.getEncryptId(),
			setting.getIsRemind(),
			setting.getIsRecommend(),
			setting.getIsNotice()
		);
	}
}
