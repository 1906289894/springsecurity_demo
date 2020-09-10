package com.wb.security.dao;

import com.wb.security.domian.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    //根据用户名称查询特定权限
    List<Permission> findByUserId(Integer id);
    //根据用户名称查询特定权限
    List<Permission> findByUrl(String url);
    //获取全部的权限
    List<Permission> findAll();
}
