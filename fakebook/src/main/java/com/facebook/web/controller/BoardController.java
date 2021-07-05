package com.facebook.web.controller;

import com.facebook.web.dto.*;
import com.facebook.web.service.BoardService;
import com.facebook.web.service.FriendsService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired
    FriendsService friendsService;

    @GetMapping("/update")
    @PreAuthorize("isAuthenticated()")
    //알아보기
    public String update_get(@AuthenticationPrincipal UserDTO userDTO, @RequestParam("bno") int bno, Model model){
        BoardDTO boardDTO = boardService.getOne(bno);
        if(boardDTO.getBwriter().equals(userDTO.getName())) {
            model.addAttribute("boardDTO", boardDTO);
            return "/board/update";
        } else {
            return "redirect:list";
        }
    }

    @PostMapping("/update")
    public String update_post(@ModelAttribute BoardDTO boardDTO){
        int bno = boardService.modify(boardDTO);
        return "redirect:read?bno="+bno;
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("bno") int bno){
        boardService.remove(bno);
        return "redirect:list";
    }


    @GetMapping("/read")
    public void read(@RequestParam("bno") int bno, Model model){
        BoardDTO boardDTO = boardService.getOne(bno);
        model.addAttribute("boardDTO", boardDTO);
    }

    @GetMapping("/list")
    public void list(@AuthenticationPrincipal UserDTO userDTO, PageRequestDTO pageRequestDTO, Model model){
        List<FriendsDTO> list = friendsService.findAllMe(userDTO.getName());

        PageResultDTO pageResultDTO = boardService.getList(pageRequestDTO);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("friendsList", list);
        model.addAttribute("pageResultDTO", pageResultDTO);
    }

    @GetMapping("/register")
    public void register_get(@AuthenticationPrincipal UserDTO userDTO,Model model){
        model.addAttribute("userDTO", userDTO);
    }

    @PostMapping("/register")
    public String register_post(@ModelAttribute BoardDTO boardDTO){
        System.out.println(boardDTO.toString());

        boardService.register(boardDTO);
        return "redirect:list";
    }
}
