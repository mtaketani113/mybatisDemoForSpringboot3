package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.NonAuthorize;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserApiController {
    
    @Autowired UserService userService;
    
    @GetMapping("/api/user")
    @NonAuthorize
    public User searchUser(@RequestParam String accessToken) {
        return userService.searchUser(accessToken);
    }
}