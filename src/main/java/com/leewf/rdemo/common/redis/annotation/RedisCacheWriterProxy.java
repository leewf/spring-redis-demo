package com.leewf.rdemo.common.redis.annotation;

import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Map;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;


public class RedisCacheWriterProxy implements RedisCacheWriter {

    private RedisCacheWriter redisCacheWriter;

    private RedisCacheMap redisCacheMap= RedisCacheMap.getInstance();

    public RedisCacheWriterProxy(RedisCacheWriter redisCacheWriter) {
//        LOGGER.info("RedisCacheWriterProxy......");

        this.redisCacheWriter = redisCacheWriter;
    }

    @Override
    public void put(String name, byte[] key, byte[] value, Duration ttl) {
        RedisCache redisCache= redisCacheMap.get(name);

        if (redisCache!=null){
            if(redisCache.timeout()>0) ttl = Duration.ofSeconds(redisCache.timeout());
            if (redisCache.upRange()>0) ttl = ttl.plusSeconds((long) (Math.random()*redisCache.upRange()));
//            LOGGER.info("cacheExpires......"+cacheExpires.cacheName()+","+cacheExpires.expireSeconds()+","+cacheExpires.upRange());
        }

        LOGGER.info("put......name:"+name+",ttl:"+ttl.getSeconds());
        redisCacheWriter.put(name,key,value,ttl);
    }

    @Override
    public byte[] get(String name, byte[] key) {
//        LOGGER.info("get......");
        return redisCacheWriter.get(name,key);
    }

    @Override
    public byte[] putIfAbsent(String name, byte[] key, byte[] value, Duration ttl) {
//        LOGGER.info("putIfAbsent......");
        return redisCacheWriter.putIfAbsent(name,key,value,ttl);
    }

    @Override
    public void remove(String name, byte[] key) {
//        LOGGER.info("remove......");
        redisCacheWriter.remove(name,key);
    }

    @Override
    public void clean(String name, byte[] pattern) {
//        LOGGER.info("clean......");
        redisCacheWriter.clean(name,pattern);
    }
}
