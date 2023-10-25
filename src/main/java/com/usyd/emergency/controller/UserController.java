package com.usyd.emergency.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
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

    @PostMapping    ("/register")
    public String registerUser(User user){
        System.out.println(111);
        if(user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getUserEmail()) ||
          StringUtils.isBlank(user.getPassword())){
            return "false";
        }
//        if(StringUtils.isNotBlank(getUser(user.getUsername()).getUsername())){
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
        userService.addUser(user);
        return "true";
    }

    @PostMapping("/update")
    public String updateUser(User user){
        if(user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getUserEmail()) ||
                StringUtils.isBlank(user.getPassword())){
            return "false";
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
        return "true";
    }
}
