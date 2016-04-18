package com.wenchukai.util;

public class StringUtil {
	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}
}
