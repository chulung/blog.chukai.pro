package com.chulung.blog.common.util;

public class NumberUtil {

	/**
	 * 判断val 是否在[from , to] 中包含 from ,to
	 * 
	 * @param val
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean isRangeIn(int val, int from, int to) {
		return val >= from && val <= to;
	}

	/**
	 * 判断val 是否不在[from , to] 中包含 from ,to
	 * 
	 * @param val
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean isRangeNotIn(int val, int from, int to) {
		return !isRangeIn(val, from, to);
	}
}
