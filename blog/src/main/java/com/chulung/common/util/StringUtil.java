package com.chulung.common.util;


public class StringUtil extends org.apache.commons.lang3.StringUtils {
	public static String toLowerCaseFirstChar(String s) {
		char[] arr = s.toCharArray();
		if (arr[0] >= 'A' && arr[0] <= 'Z') {
			arr[0] += 32;
		}
		return String.valueOf(arr);
	}
}
