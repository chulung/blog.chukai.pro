package com.wenchukai.util;

public class StringUtil {
	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
	public static boolean isBlank(String s){
		return s==null || s.trim().isEmpty();
	}
	
	public static boolean isNotBlank(String s){
		return !isBlank(s);
	}
}
