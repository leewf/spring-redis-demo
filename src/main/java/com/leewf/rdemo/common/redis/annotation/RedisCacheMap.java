package com.leewf.rdemo.common.redis.annotation;

import com.leewf.rdemo.common.redis.RedisCacheProperties;
import com.leewf.rdemo.common.utils.PacketScanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wenfeng_li
 * @createTime 2020/05/04
 * @Description:
 */
@Component
public class RedisCacheMap {
    @Autowired
    private RedisCacheProperties redisCacheProperties;

    private Map<String, RedisCache> expires = null;

    private static RedisCacheMap intance = null;
    private RedisCacheMap(){
        Set<Class> classes = null;
        try {
            classes = PacketScanUtils.findClassesByPackage(redisCacheProperties.getScanPackages(), RedisCache.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (classes.isEmpty()) return;
        Map<String, RedisCache> expires = new HashMap<String, RedisCache>(classes.size());
        for (Class clazz : classes) {
            RedisCache redisCache = (RedisCache) clazz.getDeclaredAnnotation(RedisCache.class);
            expires.put(redisCache.cacheName(), redisCache);
        }
    }
    public static RedisCacheMap getInstance(){
        if (intance == null) {
            intance = new RedisCacheMap();
        }
        return intance;
    }

    public RedisCache get(String key) {
        if (expires==null){
            return null;
        }
        return expires.get(key);
    }
}
