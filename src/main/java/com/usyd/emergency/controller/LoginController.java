package com.usyd.emergency.controller;

import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.service.LoginService;
import com.usyd.emergency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35238
 * @date 2023/7/12 0012 11:38
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    //LoginService是我们在service目录写好的接口
    private LoginService loginService;

    @PostMapping("/login")
    //ResponseResult和user是我们在domain目录写好的类
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @PostMapping("/logout")
    //ResponseResult和user是我们在domain目录写好的类
    public ResponseResult logout(){
        //登录
        return loginService.logout();
    }

}