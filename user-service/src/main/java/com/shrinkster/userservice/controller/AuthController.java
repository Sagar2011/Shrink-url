package com.shrinkster.userservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shrinkster.userservice.model.User;
import com.shrinkster.userservice.service.IUserService;
import com.shrinkster.userservice.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private IUserService userService;

    // For providing user google profile
    @GetMapping("/profile")
    public User getUserProfile(HttpServletRequest request) {
        User user = userService.loadByUsername(request);
        return user;
    }
    // for logout
    @GetMapping(value = "/logout")
    public void userLogout(HttpServletResponse response) {
        String cookiename = "Shrink_bun";
        CookieUtil.clearCookie(response, cookiename);
    }

    @GetMapping("/admin/sample")
    public String get(){
        return "admin";
    }
}
