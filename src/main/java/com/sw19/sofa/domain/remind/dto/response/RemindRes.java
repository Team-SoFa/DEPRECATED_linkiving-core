package com.sw19.sofa.domain.remind.dto.response;

import java.time.LocalDateTime;

import com.sw19.sofa.domain.linkcard.entity.LinkCard;
import com.sw19.sofa.domain.remind.entity.Remind;

public record RemindRes(
	String id,
	String linkCardId,
	String title,
	String url,
	String summary,
	String memo,
	String imageUrl,
	long views,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt,
	LocalDateTime visitedAt
) {
	public static RemindRes from(Remind remind) {
		LinkCard linkCard = remind.getLinkCard();
		return new RemindRes(
			remind.getEncryptId(),
			linkCard.getEncryptId(),
			linkCard.getTitle(),
			linkCard.getArticle().getUrl(),
			linkCard.getSummary(),
			linkCard.getMemo(),
			linkCard.getArticle().getImageUrl(),
			linkCard.getViews(),
			linkCard.getCreatedAt(),
			linkCard.getModifiedAt(),
			linkCard.getVisitedAt()
		);
	}
}
