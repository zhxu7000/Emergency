package com.usyd.emergency.service;

import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.pojo.User;
import com.usyd.emergency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MapService mapService;
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public boolean addUser(User userInput) {
        User user = new User();
        user.setUserName(userInput.getUsername());
        user.setUserEmail(userInput.getUserEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
//        user.setUserLocation(userInput.getUserLocation());
        user.setPhoneNumber(userInput.getPhoneNumber());

        user.setLongitude(userInput.getLongitude());
        user.setLatitude(userInput.getLatitude());
        userRepository.save(user);
        return true;
    }
    public boolean deleteUser(User user){
        userRepository.delete(user);
        return true;
    }

    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user;
    }

    public User findByUserEmail(String userEmail){
        User user = userRepository.findByUserEmail(userEmail);
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

    public Map<String, String> getLongitudeAndLatitude (String str) throws  Exception{

        return mapService.getLongitudeAndLatitude(str);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
