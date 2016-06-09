package com.chulung.cache;

import java.util.Map.Entry;

public class LruCacheStrategy extends CacheStrategy {

	@Override
	boolean isExpired(Element<?> element) {
		return false;
	}

	@Override
	void notifyAccess(Element<?> v) {
		v.setLastAccessMills(System.currentTimeMillis());
	}

	@Override
	void validateConfig() {

	}

	@Override
	<K, V> void notifyPut(Cache<K, V> cache) {
		if (cache.getElementCount() >= this.cacheConfig.getMaxCapacity()) {
			Entry<K, Element<V>> lowest = null;
			for (Entry<K, Element<V>> e : cache.asMap().entrySet()) {
				if (lowest == null) {
					lowest = e;
				} else {
					lowest = e.getValue().getLastAccessMills() < lowest.getValue().getLastAccessMills() ? e : lowest;
				}
			}
			cache.remove(lowest.getKey());
		}
	}

}
