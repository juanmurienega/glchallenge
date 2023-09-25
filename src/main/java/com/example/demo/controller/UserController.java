package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.test.test.dto.UserLoginResponse;
import com.test.test.dto.UserSignUpRequest;
import com.test.test.dto.UserSignUpResponse;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
    private UserService userService;
    
    @PostMapping("/sign-up")
    public UserSignUpResponse signUp (@Valid @RequestBody UserSignUpRequest newUser) {
    	return userService.createUser(newUser);
    }
    
    @GetMapping("/login")
    public UserLoginResponse login (@RequestHeader (name="Authorization") String token) {
    	return userService.login(token.split("\\s+")[1]);
    }
}
