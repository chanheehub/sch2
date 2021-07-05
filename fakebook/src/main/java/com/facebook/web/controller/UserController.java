package com.facebook.web.controller;

import com.facebook.web.dto.SignupDTO;
import com.facebook.web.dto.UserDTO;
import com.facebook.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public String signup_post(SignupDTO signupDTO) {
        System.out.println("-----------------"+signupDTO.toString());
        userService.signup(signupDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public void login() {

    }



}
