package com.leewf.rdemo.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leewf.rdemo.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liwenfeng
 * @date 2020/5/04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
