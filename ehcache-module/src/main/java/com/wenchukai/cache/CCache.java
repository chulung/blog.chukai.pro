package com.wenchukai.cache;

import org.springframework.stereotype.Component;

import com.wenchukai.bean.BaseComponent;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class CCache extends BaseComponent {
	private static CCache cCache;
	private Cache cache;

	public CCache() {
		cache = CacheManager.create().getCache("cache");
		this.logger.debug("cache {} init", cache.getName());
		cCache=this;
	}

	public void put(Object key, Object value) {
		this.logger.debug("put key: {} value:{}", key, value);
		this.cache.put(new Element(key, value));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param timeToLive
	 *            过期时间 秒
	 */
	public void put(Object key, Object value, int timeToLive) {
		this.logger.debug("put key: {} value:{} timeTolive {}s", key, value, timeToLive);
		this.cache.put(new Element(key, value, 0, timeToLive));
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Object key) {
		this.logger.debug("get key: {}", key);
		Element e = this.cache.get(key);
		return e == null ? null : (T) e.getObjectValue();
	}

	public void remove(Object key) {
		this.logger.debug("remove key: {}", key);
		this.cache.remove(key);
	}

	public static CCache getInstance() {
		return cCache;
	}
}
