package com.leewf.rdemo.common.redis;


import com.leewf.rdemo.common.redis.annotation.RedisCacheWriterProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

/**DefaultRedisCacheWriter
 *
 *
 *
 * Created with IntelliJ IDEA.
 *
 * @author liwenfeng
 * @date 2019/6/19
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    RedisProperties redisProperties;
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        LOGGER.info("redisCacheManager......");
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(redisProperties.getTimeout())
                //key序列化配置
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                //value序列化配置
                .serializeValuesWith(RedisSerializationContext
                            .SerializationPair
                            .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .builder(new RedisCacheWriterProxy(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory)))
                .cacheDefaults(redisCacheConfiguration).build();
    }
}


