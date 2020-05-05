package com.leewf.rdemo.web.controller;

import com.leewf.rdemo.application.service.UserApplicationService;
import com.leewf.rdemo.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wenfeng_li
 * @createTime 2020/05/02
 * @Description:
 */


@Slf4j
@RestController
@RequestMapping("user")
public class UserConroller {
    @Autowired
    private UserApplicationService userApplicationService;

    @GetMapping("/find/name")
    public User findUserByLoginName(@RequestParam(name = "loginName", required = false) String loginName){
        User user = userApplicationService.findUserByLoginName(loginName);
        return user;
    }
}
