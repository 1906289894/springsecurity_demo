package com.wb.security.auth;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class VAuthenticationProvider implements AuthenticationProvider {

    Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VUserDetailsService vUserDetailsService;

    /**
     * 首先，在用户登录的时候，系统将用户输入的的用户名和密码封装成一个Authentication对象
     * 然后，根据用户名去数据源中查找用户的数据，这个数据是封装成的VUserDetails对象
     * 接着，将两个对象进行信息比对，如果密码正确，通过校验认证
     * 最后，将用户信息（含身份信息、细节信息、密码、权限等）封装成一个对象，此处参考UsernamePasswordAuthenticationToken
     * 最最后，会将这个对象交给系统SecurityContextHolder中（功能类似Session），以便后期随时取用
     */
    @Override
    public Authentication authenticate(Authentication authentication)  {
        //获取authenticate用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.info("VAuthenticationProvider authenticate login user [username={}, password={}]", username, password);
        VUserDetails userDetails =(VUserDetails)vUserDetailsService.loadUserByUsername(username);
        //todo gson转换问题
       // logger.info("VAuthenticationProvider authenticate vUserDetails [vUserDetails={}]", gson.toJson(userDetails));
        if (userDetails == null){
            throw new BadCredentialsException("用户没有找到");
        }
        if (!password.equals(userDetails.getPassword())) {
            logger.info("VAuthenticationProvider authenticate BadCredentialsException [inputPassword={}, DBPassword={}]", password, userDetails.getPassword());
            throw new BadCredentialsException("密码错误");
        }
        //校验成功通过后，封装UsernamePasswordAuthenticationToken返回
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
