package com.facebook.web.controller;

import com.facebook.web.dto.*;
import com.facebook.web.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/my")
    public void my(@AuthenticationPrincipal UserDTO userDTO, Model model){
        model.addAttribute("userDTO", userDTO);
    }
}
