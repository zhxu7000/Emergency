package com.usyd.emergency.service;

import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.utils.JwtUtil;
import com.usyd.emergency.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    //先在SecurityConfig，使用@Bean注解重写官方的authenticationManagerBean类，然后这里才能注入成功
    private AuthenticationManager authenticationManager;

    @Autowired
    //RedisCache是我们在utils目录写好的类
    private RedisCache redisCache;

    //ResponseResult和user是我们在domain目录写好的类
    public ResponseResult login(User user) {

        //用户在登录页面输入的用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserEmail(),user.getPassword());

        //获取AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断上面那行的authenticate是否为null，如果是则认证没通过，就抛出异常
        if(Objects.isNull(authenticate)){
            throw new ConflictException(XError.USERNAME_OR_PASSWORD_INCORRECT.getCode(), XError.USERNAME_OR_PASSWORD_INCORRECT.getMsg());
        }

        //如果认证通过，就使用userid生成一个jwt，然后把jwt存入ResponseResult后返回
        User loginUser = (User) authenticate.getPrincipal();
        String userid = loginUser.getUserId() + "";
        String jwt = JwtUtil.createJWT(userid);

        //把完整的用户信息存入redis，其中userid作为key，注意存入redis的时候加了前缀 login:
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        map.put("user_id", userid);
        map.put("is_admin", loginUser.getIsAdmin());
        map.put("user_name", loginUser.getUserName());
        map.put("lat", loginUser.getLatitude());
        map.put("lng", loginUser.getLongitude());
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"login successful",map);
    }

    public ResponseResult logout() {
        
        // get user info
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User loginUser = (User) authentication.getPrincipal();
        Integer userId = loginUser.getUserId();
        // delete uid from redis
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(200, "logout successful.");
        
    }
}
