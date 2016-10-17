package com.chulung.blog.enumerate;

public interface ValuedEnum<T> {
	/**
	 * 枚举对应数字值
	 * @return
	 */
	int getValue();
    /**
     * 枚举描述
     * @return
     */
    String getDesc();
}