package com.wchukai.conf;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by wchukai on 2017/3/3.
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfig {

    private GuavaCache forever;
    private GuavaCache oneMin;
    private GuavaCache halfhour;

    public CacheConfig() {
        forever = new GuavaCache("forever", CacheBuilder.newBuilder().maximumSize(100).build());
        oneMin = new GuavaCache("oneMin", CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(1, TimeUnit.MINUTES).build());
        halfhour = new GuavaCache("halfhour", CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).maximumSize(100).build());
    }

    @Bean
    @Primary
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(forever, oneMin, halfhour));
        return cacheManager;
    }

    @Bean
    public Cache forever() {
        return this.forever;
    }

    @Bean
    public Cache oneMin() {
        return oneMin;
    }

    @Bean
    public Cache halfhour() {
        return halfhour;
    }
}
