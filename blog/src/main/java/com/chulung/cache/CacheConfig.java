package com.chulung.cache;

public class CacheConfig {
	private int maxCapacity;

	public CacheConfig(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

}
