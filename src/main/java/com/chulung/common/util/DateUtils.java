package com.chulung.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 扩展增加一个默认格式化日期方法
 * 
 * @author ChuKai
 *
 */
public class DateUtils extends DateFormatUtils {
	/**
	 * 默认日期格式
	 */
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 转化默认格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Date date) {
		return format(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 转化为默认格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static String format(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}

	public static  Date toDate(LocalDateTime source) {
		return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
	}
}
