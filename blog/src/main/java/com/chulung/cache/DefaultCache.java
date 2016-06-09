package com.chulung.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DefaultCache<K, V> implements Cache<K, V> {
	private Map<K, Element<V>> map = new ConcurrentHashMap<>();
	protected CacheConfig cacheConfig;
	private CacheStrategy cacheStrategy;

	public DefaultCache(CacheConfig cacheConfig, CacheStrategy cacheStrategy) {
		this.cacheConfig = cacheConfig;
		this.cacheStrategy = cacheStrategy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		Element<?> e = this.map.get(key);
		if (e == null || cacheStrategy.isExpired(e)) {
			return null;
		}
		cacheStrategy.notifyAccess(e);
		return (V) e.getValue();
	}

	@Override
	public boolean put(K key, V value) {
		this.cacheStrategy.notifyPut(this);
		return this.map.put(key, new Element<V>(value)) != null;
	}

	@Override
	public void clean() {
		map.clear();
	}

	@Override
	public V remove(K key) {
		Element<V> e = this.map.remove(key);
		return e == null ? null : e.getValue();
	}

	@Override
	public boolean putIfAbsent(K key, V value) {
		return this.map.putIfAbsent(key, new Element<V>(value)) != null;
	}

	@Override
	public int getElementCount() {
		return this.map.size();
	}

	@Override
	public Map<K, Element<V>> asMap() {
		return this.map;
	}

}
