package com.wb.security.auth.authorization;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限管理判断器|校验用户是否有权限访问请求资源
 * @author Veiking
 */
@Component
public class VAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object bj, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //当前用户所具有的权限
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        //访问资源所需要的权限信息
        Collection<ConfigAttribute> needAuthoritieList = collection;
        //依次循环对比，发现有问题即返回
        for (ConfigAttribute configAttribute : needAuthoritieList) {
            String needattribute = configAttribute.getAttribute();
            for (GrantedAuthority userAuthority : userAuthorities) {
                String authority = userAuthority.getAuthority();
                if (needattribute.equals(authority)){
                    return;
                }
            }
        }

        //执行到这里说明没有匹配到权限
        throw new AccessDeniedException("权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
