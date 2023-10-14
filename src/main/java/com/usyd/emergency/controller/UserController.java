package com.usyd.emergency.controller;

import com.usyd.emergency.exception.ConflictException;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
