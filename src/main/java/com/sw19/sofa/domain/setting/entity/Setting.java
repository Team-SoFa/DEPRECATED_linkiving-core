package com.sw19.sofa.domain.setting.entity;

import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.util.EncryptionUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	private Boolean isRemind;
	private Boolean isRecommend;
	private Boolean isNotice;

	@Builder
	public Setting(Member member, Boolean isRemind, Boolean isRecommend, Boolean isNotice) {
		this.member = member;
		this.isRemind = isRemind;
		this.isRecommend = isRecommend;
		this.isNotice = isNotice;
	}

	public String getEncryptId() {
		return EncryptionUtil.encrypt(this.id);
	}

	public void toggleRemindAlarm() {
		this.isRemind = !this.isRemind;
	}

	public void toggleRecommendAlarm() {
		this.isRecommend = !this.isRecommend;
	}

	public void toggleNoticeAlarm() {
		this.isNotice = !this.isNotice;
	}

}
