package com.chulung.cache;

public class Element<V> {
	private V value;
	private int hitCount;
	private long createMills;
	private long lastAccessMills;

	public Element(V v) {
		this.setCreateMills(System.currentTimeMillis());
		this.value = v;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public long getLastAccessMills() {
		return lastAccessMills;
	}

	public void setLastAccessMills(long lastAccessMills) {
		this.lastAccessMills = lastAccessMills;
	}

	public long getCreateMills() {
		return createMills;
	}

	public void setCreateMills(long createMills) {
		this.createMills = createMills;
	}

}