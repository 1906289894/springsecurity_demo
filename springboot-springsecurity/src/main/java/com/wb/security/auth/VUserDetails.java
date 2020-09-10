package com.wb.security.auth;

import com.google.gson.Gson;
import com.wb.security.domian.Permission;
import com.wb.security.domian.SysRole;
import com.wb.security.domian.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class VUserDetails extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //用户角色权限列表
    private List<SysRole> sysRoles = null;
    //用户资源权限列表
    private List<Permission> permissions = null;

    public VUserDetails(SysUser user,List<SysRole> sysRoles,List<Permission> permissions) {
        super(user);
        this.sysRoles = sysRoles;
        this.permissions = permissions;
    }

    /**
     * 获取用户权限列表方法
     * 可以理解成，返回了一个List<String>，之后所谓的权限控制、鉴权，其实就是跟这个list里的String进行对比
     * 这里处理了角色和资源权限两个列表，可以这么理解，
     * 角色是权限的抽象集合，是为了更方便的控制和分配权限，而真正颗粒化细节方面，还是需要资源权限自己来做
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        StringBuilder authoritiesBuilder = new StringBuilder("");
        //拿到权限类
        List<SysRole> tempRoleList = this.getSysRoleList();
        if (null != tempRoleList){
            for (SysRole sysRole : tempRoleList){
                //获得角色列表
                authoritiesBuilder.append(",").append(sysRole.getName());
            }
        }
        List<Permission> tempPermissionList = this.getsPermissionList();
        if (null!=tempPermissionList) {
            for (Permission permission : tempPermissionList) {
                //获得用户权限
                authoritiesBuilder.append(",").append(permission.getName());
            }
        }
        String authoritiesStr = "";
        if (authoritiesBuilder.length()>0){
            authoritiesStr = authoritiesBuilder.deleteCharAt(0).toString();
        }
        logger.info("VUserDetails getAuthorities [authoritiesStr={} ", authoritiesStr);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr);
    }

    /**
     * 判断账号是否已经过期，默认没有过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否被锁定，默认没有锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断信用凭证是否过期，默认没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断账号是否可用，默认可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    public List<SysRole> getSysRoleList(){
       return sysRoles;
    }

    public void setSysRoleList(List<SysRole> sRoleList) {
        this.sysRoles = sRoleList;
    }

    public List<Permission> getsPermissionList() {
        return permissions;
    }

    public void setsPermissionList(List<Permission> sPermissionList) {
        this.permissions = sPermissionList;
    }

}
