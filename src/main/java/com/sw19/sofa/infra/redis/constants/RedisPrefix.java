package com.sw19.sofa.infra.redis.constants;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisPrefix {
	REFRESH_TOKEN(Duration.ofDays(180)),
	SING_UP(Duration.ofMinutes(10)),
	PROFILE(Duration.ofDays(1)),
	SEARCH_KEYWORD_HISTORY(null),
	SEARCH_TAG_HISTORY(null);

	final Duration duration;
}
