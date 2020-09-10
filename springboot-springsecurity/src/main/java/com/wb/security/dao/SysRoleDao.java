package com.wb.security.dao;

import com.wb.security.domian.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleDao {
    /**
     * 根据用户ID获取角色列表
     * @param sUserId
     * @return
     */
    @Select(value=" SELECT sr.* FROM sys_role sr " +
            " LEFT JOIN sys_role_user sur ON sr.id = sur.sys_role_id " +
            " LEFT JOIN sys_user su ON sur.sys_user_id = su.id " +
            " WHERE su.id = #{sUserId} ")
    public List<SysRole> findSRoleListBySUserId(int sUserId);

    /**
     * 根据资源路径获取角色列表
     * @param sPermissionUrl
     * @return
     */
    @Select(value=" SELECT sr.* FROM sys_role sr " +
            " LEFT JOIN sys_permission_role srp ON sr.id = srp.role_id " +
            " LEFT JOIN sys_permission sp ON srp.permission_id = sp.id " +
            " WHERE sp.url = #{sPermissionUrl} ")
    public List<SysRole> findSRoleListBySPermissionUrl(String sPermissionUrl);
}
