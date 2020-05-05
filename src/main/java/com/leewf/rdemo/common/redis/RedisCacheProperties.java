package com.leewf.rdemo.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wenfeng_li
 * @createTime 2020/05/05
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.cache")
public class RedisCacheProperties {

    private String scanPackages;
    private Long defaultTimeout;
    private Long defaultUpRange;

}
