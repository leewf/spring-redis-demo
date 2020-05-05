package com.leewf.rdemo.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leewf.rdemo.domain.entity.User;
import com.leewf.rdemo.domain.mapper.UserMapper;
import com.leewf.rdemo.domain.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author wenfeng_li
 * @createTime 2020/05/04
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

