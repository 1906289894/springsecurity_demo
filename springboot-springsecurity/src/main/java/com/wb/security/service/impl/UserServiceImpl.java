package com.wb.security.service.impl;

import com.wb.security.dao.PermissionMapper;
import com.wb.security.dao.UserMapper;
import com.wb.security.domian.Permission;
import com.wb.security.domian.SysUser;
import com.wb.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public String findByIdUser(Integer id) {
        SysUser user = userMapper.findByIdUser(id);
        return user.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        SysUser user = userMapper.findByUserName(username);
        if (null != null){
            //获取用户权限
            List<Permission> permissions = permissionMapper.findByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission:permissions){
                if (permissions != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
        }else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
