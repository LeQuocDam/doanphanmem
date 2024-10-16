package com.example.DoAnPhanMem.Controller;

import com.example.DoAnPhanMem.Entity.User;
import com.example.DoAnPhanMem.Request.UserRequest;
import com.example.DoAnPhanMem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public User createUser(@RequestBody @Valid UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/user/read")
    public List<User> readUser(){
        return userService.readUser();
    }

    @GetMapping("/user/{userName}")
    public User readUser(@PathVariable String userName){
        return (User) userService.readUser();
    }

    @PutMapping("/user/{userName}")
    public User readUser(@RequestBody UserRequest userRequest){
        return (User) userService.readUser();
    }
}
