package com.usyd.emergency.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.UpdateRequest;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.service.MailService;
import com.usyd.emergency.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailService mailService;
    @RequestMapping("/test")
    public String test() {
        throw new ConflictException(-1, "error");
    }
    @RequestMapping("/user/all")
    public Iterable<User> getAll() {
        return userService.getAll();
    }

//    @RequestMapping("/add")
//    public boolean addUser() {
//        return userService.addUser();
//    }

    @RequestMapping("/get")
    public User getUser(String userName) {
        return userService.findByUserName("yuri");
    }

    @PostMapping("/update")
    public ResponseResult updateUser(@RequestBody UpdateRequest updateInfo) {

        System.out.println(updateInfo);
        // 校验必须提供的字段
        if (updateInfo == null || StringUtils.isBlank(updateInfo.getUserName()) || StringUtils.isBlank(updateInfo.getPassword())) {
            throw new ConflictException(XError.USERNAME_OR_PASSWORD_INCORRECT.getCode(), "Username and Password cannot be blank");
        }

        // 从数据库中查找用户
        User existingUser = userService.findByUserName(updateInfo.getUserName());
        if (existingUser == null || !passwordEncoder.matches(updateInfo.getPassword(), existingUser.getPassword())) {
            throw new ConflictException(XError.USERNAME_OR_PASSWORD_INCORRECT.getCode(), "Username or Password is incorrect");
        }

        // 如果提供了新的用户名，则更新用户名
        if (StringUtils.isNotBlank(updateInfo.getNewUserName())) {
            if (userService.findByUserName(updateInfo.getNewUserName()) != null) {
                throw new ConflictException(XError.USERNAME_ALREADY_EXISTS.getCode(), "New Username already exists");
            }
            existingUser.setUserName(updateInfo.getNewUserName());
        }

        // 如果提供了新密码，则更新密码
        if (StringUtils.isNotBlank(updateInfo.getNewPassword())) {
            existingUser.setPassword(passwordEncoder.encode(updateInfo.getNewPassword()));
        }

        // 根据提供的其他字段更新用户信息
        if (StringUtils.isNotBlank(updateInfo.getUserEmail())) {
            existingUser.setUserEmail(updateInfo.getUserEmail());
        }
        if (StringUtils.isNotBlank(updateInfo.getPhoneNumber())) {
            existingUser.setPhoneNumber(updateInfo.getPhoneNumber());
        }
        if (StringUtils.isNotBlank(updateInfo.getLocation())) {
//            existingUser.setUserLocation(updateInfo.getLocation());
            Map<String, String> res = new HashMap<>();
            try {
                res = userService.getLongitudeAndLatitude(updateInfo.getLocation());
                System.out.println("res" + res);
            } catch (Exception e) {
                System.out.println("location error");
                throw new RuntimeException(e);
            }

            existingUser.setLatitude(res.get("Latitude"));
            existingUser.setLongitude(res.get("Longitude"));
        }

        userService.updateUser(existingUser);
        return new ResponseResult(200, "user info updated successfully");
    }

    @PostMapping("/register")
    public ResponseResult registerUser(@RequestBody User user){
        System.out.println(user.getUserEmail());
        if (StringUtils.isNotBlank(user.getUserEmail())) {
            if (userService.findByUserEmail(user.getUserEmail()) != null) {
                throw new ConflictException(XError.USERNAME_ALREADY_EXISTS.getCode(), "Email already exists");
            }
        }
        if(user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getUserEmail()) ||
                StringUtils.isBlank(user.getPassword())){
            throw new ConflictException(XError.USERNAME_OR_PASSWORD_INCORRECT.getCode(), "fields can not be null");
        }
        Map<String, String> res = new HashMap<>();
        try {
            res = userService.getLongitudeAndLatitude(user.getUserLocation());
            System.out.println("res" + res);
        } catch (Exception e) {
            System.out.println("location error");
            throw new RuntimeException(e);
        }

        user.setLatitude(res.get("Latitude"));
        user.setLongitude(res.get("Longitude"));
        userService.addUser(user);
        return new  ResponseResult(200, "user registered successfully");
    }


    @GetMapping("/sendEmail")
    public ResponseResult sendEmail(String title, String content){
        if(mailService.sendEmail(title, content)){
            return new  ResponseResult(200, "Sent Email Successfully");
        }
        return new  ResponseResult(500, "Sent Email Failed");
    }
}
