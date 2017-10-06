package uy.edu.ort.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.edu.ort.util.MapTimeoutCacheManager;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheContext {
    @Value("${service.cache.timeout}")
    private long cacheTimeout;
    @Value("${service.cache.max-size}")
    private long cacheMaximunSize;
    @Value("${service.cache.time-unit}")
    private String timeUnit;

    @Bean
    public CacheManager cacheManager() {
        MapTimeoutCacheManager cacheManager = new MapTimeoutCacheManager();
        cacheManager.setCacheTimeout(cacheTimeout);
        cacheManager.setTimeUnit(TimeUnit.valueOf(timeUnit));
        cacheManager.setMaximumSize(cacheMaximunSize);
        return cacheManager;
    }

}
