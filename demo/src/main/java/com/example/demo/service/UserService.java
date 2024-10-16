package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createRequest(UserRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }
}
