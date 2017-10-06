package uy.edu.ort.util;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class MapTimeoutCacheManager extends ConcurrentMapCacheManager {
    private Long cacheTimeout;
    private Long maximumSize;
    private TimeUnit timeUnit;

    public MapTimeoutCacheManager() {
    }

    @Override
    protected Cache createConcurrentMapCache(final String name) {
        ConcurrentMap map = CacheBuilder.newBuilder()
                .expireAfterWrite(cacheTimeout, timeUnit)
                .maximumSize(maximumSize)
                .build().asMap();
        Cache cache = new ConcurrentMapCache(name, map, false);
        return cache;
    }

    public Long getCacheTimeout() {
        return cacheTimeout;
    }

    public void setCacheTimeout(Long cacheTimeout) {
        this.cacheTimeout = cacheTimeout;
    }

    public Long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Long maximumSize) {
        this.maximumSize = maximumSize;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}