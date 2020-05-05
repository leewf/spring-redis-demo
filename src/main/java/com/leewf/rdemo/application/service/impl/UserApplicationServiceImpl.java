package com.leewf.rdemo.application.service.impl;

import com.leewf.rdemo.application.redis.cache.UserRedisCache;
import com.leewf.rdemo.application.service.UserApplicationService;
import com.leewf.rdemo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenfeng_li
 * @createTime 2020/05/04
 * @Description:
 */
@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    @Autowired
    private UserRedisCache userRedisCache;

    @Override
    public User findUserByLoginName(String loginName) {
        return userRedisCache.getOne(loginName);
    }
}
