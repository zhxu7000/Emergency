package com.usyd.emergency;


import com.usyd.emergency.repository.UserRepository;
import com.usyd.emergency.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Test
    void findUser() {
        System.out.println(userRepository.findByUserName("yuri"));
    }



}
