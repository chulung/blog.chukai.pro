package com.chulung.cache;

import java.util.Map;

public interface Cache<K, V> {

	V get(K key);

	boolean put(K key, V value);

	void clean();

	V remove(K key);

	boolean putIfAbsent(K key, V value);

	int getElementCount();

	Map<K, Element<V>> asMap();

}
