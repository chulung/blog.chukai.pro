package com.chulung.website.constant;

import java.time.Instant;

public class Constants {
	/**
	 * 默认分页大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final  long  FIRST_ARTICLE_CREATE_TIME=Instant.parse("2014-07-01T09:00:00.00Z").getEpochSecond();
}
