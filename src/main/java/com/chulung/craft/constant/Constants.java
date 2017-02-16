package com.chulung.craft.constant;

import java.time.Instant;

public class Constants {
	/**
	 * 默认分页大小
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	/** 用户追踪唯一标识 */
	public static final String TUID = "tuid";

	public static final  long  FIRST_ARTICLE_CREATE_TIME=Instant.parse("2014-07-01T09:00:00.00Z").getEpochSecond();
}
