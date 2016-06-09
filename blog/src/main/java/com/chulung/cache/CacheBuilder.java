package com.chulung.cache;

import static com.chulung.common.util.Preconditions.checkArgument;
import static com.chulung.common.util.Preconditions.checkNotNull;

@SuppressWarnings("rawtypes")
public final class CacheBuilder {

	private CacheConfig cacheConfig;
	private CacheStrategy cacheStrategy;

	private CacheBuilder() {
	}

	public static CacheBuilder config(int maxCapacity) {
		CacheBuilder cacheBuilder = new CacheBuilder();
		checkArgument(maxCapacity > 0);
		cacheBuilder.cacheConfig = new CacheConfig(maxCapacity);
		return cacheBuilder;
	}

	public CacheBuilder addLiveMillesCacheStrategy(long timeToLiveSeconds, boolean isRefreshAfterAccess) {
		checkArgument(timeToLiveSeconds > 0);
		this.cacheStrategy = new LiveMillesCacheStrategy(timeToLiveSeconds, isRefreshAfterAccess);
		return this;
	}

	public CacheBuilder addLruCacheStrategy() {
		this.cacheStrategy = new LruCacheStrategy();
		return this;
	}

	public Cache generateCache() {
		checkNotNull(cacheConfig);
		checkNotNull(cacheStrategy);
		return new DefaultCache<Object, Object>(cacheConfig,cacheStrategy);
	}
}
