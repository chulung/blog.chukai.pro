package com.chulung.cache;


public abstract class CacheStrategy {
	protected CacheConfig cacheConfig;

	abstract boolean isExpired(Element<?> element);

	abstract void notifyAccess(Element<?> v);

	abstract void validateConfig();

	abstract  <K, V> void notifyPut(Cache<K,V>  cache);
}
