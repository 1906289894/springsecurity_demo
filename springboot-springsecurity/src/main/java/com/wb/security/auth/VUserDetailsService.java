package com.wb.security.auth;

import com.wb.security.domian.Permission;
import com.wb.security.domian.SysRole;
import com.wb.security.domian.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VUserDetailsService implements UserDetailsService {
    @Autowired
    SecurityDataService securityDataService;

    /**
     * 根据用户输入的用户名返回数据源中用户信息的封装，返回一个UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户是否存在
        SysUser sUser = securityDataService.findSUserByName(username);
        //todo 用户不存在会报错，待解决
        //查询用户角色列表
        List<SysRole> sRoleList = securityDataService.findSRoleListBySUserId(sUser.getId());
        //查询用户资源权限列表
        List<Permission> sPermissionList = securityDataService.findSPermissionListBySUserId(sUser.getId());
        return new VUserDetails(sUser,sRoleList,sPermissionList);
    }

}
