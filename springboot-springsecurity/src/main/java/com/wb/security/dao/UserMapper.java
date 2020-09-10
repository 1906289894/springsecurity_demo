package com.wb.security.dao;

import com.wb.security.domian.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //按id查询用户
    SysUser findByIdUser(Integer id);
    //按用户名查询用户
    SysUser findByUserName(String username);
}
