package com.facebook.web.controller;

import com.facebook.web.dto.FriendsDTO;
import com.facebook.web.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Autowired
    FriendsService friendsService;


    @PostMapping("/register")
    public void register_post(@ModelAttribute FriendsDTO friendsDTO){
        System.out.println(friendsDTO.toString());

        friendsService.register(friendsDTO);
    }
}
