package com.usyd.emergency.service;

import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public boolean addUser(String name, String  password, String  email, String phoneNumber, String location) {
        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setPassword(password);
        user.setUserLocation(location);
        userRepository.save(user);
        return true;
    }

    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return user;
    }

}
