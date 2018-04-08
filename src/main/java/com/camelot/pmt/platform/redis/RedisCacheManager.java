package com.camelot.pmt.platform.redis;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("redisCacheManager")
public class RedisCacheManager implements CacheManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisShiroCache<K, V>(name, redisTemplate);
    }

}
