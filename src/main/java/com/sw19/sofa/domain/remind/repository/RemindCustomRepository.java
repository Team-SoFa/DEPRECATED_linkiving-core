package com.sw19.sofa.domain.remind.repository;

import java.util.List;

import com.sw19.sofa.domain.remind.entity.Remind;
import com.sw19.sofa.domain.remind.enums.RemindSortBy;
import com.sw19.sofa.global.common.dto.enums.SortOrder;

public interface RemindCustomRepository {
	List<Remind> search(
		Long memberId,
		Long lastId,
		int limit,
		RemindSortBy sortBy,
		SortOrder sortOrder
	);
}
