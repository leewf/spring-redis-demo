package com.leewf.rdemo.application.redis.cache;



import com.leewf.rdemo.common.redis.annotation.RedisCache;
import com.leewf.rdemo.domain.entity.User;
import com.leewf.rdemo.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RedisCache(cacheName = UserRedisCache.CacheName,timeout = 3600,upRange = 180)
public class UserRedisCache {
    protected final static String CacheName = "USER";
    @Autowired
    private UserService userService;

    @Cacheable(value = CacheName, key = "'"+CacheName+":'+#loginName",unless="#result==null")
    public User getOne(String loginName) {
        return userService.lambdaQuery().eq(User::getLoginName,loginName).one();
    }
    @CachePut(value = CacheName, key = "'"+CacheName+":'+#user.loginName")
    public User put(User user) {
        userService.saveOrUpdate(user);
        return user;
    }
    @CacheEvict(value = CacheName, key = "'"+CacheName+":'+#loginName")
    public void clear(String loginName){
    }
    @CacheEvict(value = CacheName, allEntries = true)
    public void clearAll() {
    }
}
