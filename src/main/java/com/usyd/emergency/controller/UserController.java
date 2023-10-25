package com.usyd.emergency.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.usyd.emergency.constant.XError;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

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

    @PostMapping("/register")
    public ResponseResult registerUser(User user){
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

    @PostMapping("/update")
    public ResponseResult updateUser(User user){
        if(user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getUserEmail()) ||
                StringUtils.isBlank(user.getPassword())){
            throw new ConflictException(XError.USERNAME_OR_PASSWORD_INCORRECT.getCode(), "fields can not be null while updating");
        }
        //        if(StringUtils.isBlank(getUser(user.getUsername()).getUsername())){
//
//            return "false";
//        }
//        userService.addUser(user.getUsername(),user)
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
        userService.deleteUser(userService.findByUserName(user.getUsername()));
        userService.addUser(user);
        return new  ResponseResult(200, "user info updated successfully");
    }
    @GetMapping("/sendEmail")
    public String updateUser(String userName, String title, String content){
        //激活邮件
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("content",content);
        //http://localhost:8080/community/activation/101/code
        User user = userService.findByUserName(userName);
        mailClient.sendMail(user.getUserEmail(), "Announcement", content);

    }

}
