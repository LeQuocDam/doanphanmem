package com.example.DoAnPhanMem.Service;

import com.example.DoAnPhanMem.Entity.User;
import com.example.DoAnPhanMem.Repository.UserRepository;
import com.example.DoAnPhanMem.Request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequest userRequest){
        if (userRepository.existsByUserName(userRequest.getUserName()))
            throw new RuntimeException("UserName existed");
        User user = new User();
        user.setUserName(userRequest.getUserName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());

        return userRepository.save(user);
    }

    public List<User> readUser(){
        return userRepository.findAll();
    }

    public Optional<User> readUser(String userName){
        return userRepository.findById(userName);
    }

    public User updateUser()
}
