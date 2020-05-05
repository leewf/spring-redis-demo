package com.leewf.rdemo.common.redis.annotation;



import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author wenfeng_li
 * @createTime 2020/05/04
 * @Description:
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface RedisCache {
    /**
     * 定义缓存名称
     * @return
     */
    public String cacheName();
    /**
     * 获取缓存保留时间（过期时长），单位：秒
     * @return
     */
    public long timeout() default 0;
    /**
     * 过期时长上行浮动范围，单位：秒
     * @return
     */
    public long upRange() default 0;
}
