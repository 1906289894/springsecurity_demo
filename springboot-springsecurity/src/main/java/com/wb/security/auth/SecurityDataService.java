package com.wb.security.auth;

import com.wb.security.dao.PermissionMapper;
import com.wb.security.dao.SysRoleDao;
import com.wb.security.dao.UserMapper;
import com.wb.security.domian.Permission;
import com.wb.security.domian.SysRole;
import com.wb.security.domian.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Security 数据服务
 */
@Component
public class SecurityDataService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private SysRoleDao sysRoleDao;

    public SysUser findSUserByName(String name) {
        return userMapper.findByUserName(name);
    }

    public List<SysRole> findSRoleListBySUserId(int UserId) {
        return sysRoleDao.findSRoleListBySUserId(UserId);
    }

    public List<SysRole> findSRoleListBySPermissionUrl(String sPermissionUrl) {
        return sysRoleDao.findSRoleListBySPermissionUrl(sPermissionUrl);
    }
    public List<Permission> findSPermissionListBySUserId(int UserId) {
        return permissionMapper.findByUserId(UserId);
    }

    public List<Permission> findSPermissionListBySPermissionUrl(String sPermissionUrl) {
        return permissionMapper.findByUrl(sPermissionUrl);
    }
}

