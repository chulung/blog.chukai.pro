package com.chulung.cache;

import com.chulung.common.util.Preconditions;

public class LiveMillesCacheStrategy extends CacheStrategy {
	private long timeToLiveMilles;
	private boolean isRefreshAfterAccess;

	public LiveMillesCacheStrategy(long timeToLiveSeconds, boolean isRefreshAfterAccess) {
		this.timeToLiveMilles = timeToLiveSeconds * 1000;
		this.isRefreshAfterAccess = isRefreshAfterAccess;
	}

	public long getTimeToLiveMilles() {
		return timeToLiveMilles;
	}

	public void setTimeToLiveMilles(long timeToLiveMilles) {
		this.timeToLiveMilles = timeToLiveMilles;
	}

	@Override
	public boolean isExpired(Element<?> element) {
		return (element.getCreateMills() + this.timeToLiveMilles) < System.currentTimeMillis();
	}

	@Override
	public void validateConfig() {
		Preconditions.checkState(timeToLiveMilles <= 0, "timeToLiveMilles must > 0 !");
	}

	public boolean isRefreshAfterAccess() {
		return isRefreshAfterAccess;
	}

	public void setRefreshAfterAccess(boolean isRefreshAfterAccess) {
		this.isRefreshAfterAccess = isRefreshAfterAccess;
	}

	@Override
	void notifyAccess(Element<?> v) {
		if (isRefreshAfterAccess) {
			v.setCreateMills(System.currentTimeMillis());
		}
	}

	@Override
	<K, V> void notifyPut(Cache<K, V> cache) {
		if (cache.getElementCount() >= this.cacheConfig.getMaxCapacity()) {
			throw new CapacityOutOfBoundsException();
		}
	}

}
