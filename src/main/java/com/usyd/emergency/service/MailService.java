package com.usyd.emergency.service;

import com.usyd.emergency.pojo.User;
import com.usyd.emergency.utils.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: JunyuLiang
 * @Date: 2023/10/25 - 10 - 25 -19:35
 */
@Service
public class MailService {

    @Autowired
    MailClient mailClient;
    @Autowired
    UserService userService;
    public boolean sendEmail(String title, String content){
        Iterable<User> users = userService.getAll();
        for (User user : users) {
            mailClient.sendMail(user.getUserEmail(), title ,content);
        }
        return true;
    }
}
