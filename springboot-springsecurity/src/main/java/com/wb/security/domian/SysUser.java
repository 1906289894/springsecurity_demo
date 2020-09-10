package com.wb.security.domian;

import lombok.Data;

import java.util.List;

@Data
public class SysUser {

    //主键id
    private Integer id;
    //用户名
    private String username;
    //登录密码
    private String password;

    private List<SysRole> roles;

    public SysUser(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
