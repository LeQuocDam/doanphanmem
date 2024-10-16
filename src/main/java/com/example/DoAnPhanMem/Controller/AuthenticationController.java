package com.example.DoAnPhanMem.Controller;

import com.example.DoAnPhanMem.Entity.User;
import com.example.DoAnPhanMem.Request.AuthenticationRequest;
import com.example.DoAnPhanMem.Request.IntrospectRequest;
import com.example.DoAnPhanMem.Request.UserRequest;
import com.example.DoAnPhanMem.Service.AuthenticatinService;
import com.example.DoAnPhanMem.Service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticatinService authenticatinService;

    @PostMapping("/user/auth/signin")
    public String SignIn(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticatinService.SignIn(authenticationRequest);
    }

    @PostMapping("/user/auth/introspect")
    public boolean Introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return authenticatinService.Introspect(introspectRequest);
    }
}
