package com.leewf.rdemo.application.service;

import com.leewf.rdemo.domain.entity.User;

/**
 * @author wenfeng_li
 * @createTime 2020/05/04
 * @Description:
 */
public interface UserApplicationService {

    User findUserByLoginName(String loginName);
}
