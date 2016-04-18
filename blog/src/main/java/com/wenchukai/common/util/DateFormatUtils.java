package com.wenchukai.common.util;

import java.util.Date;

/**
 * 扩展增加一个默认格式化日期方法
 * @author ChuKai
 *
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {
	/**
	 * 默认日期格式
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String format(Date date) {
		return format(date, DEFAULT_DATE_FORMAT);
	}
}
