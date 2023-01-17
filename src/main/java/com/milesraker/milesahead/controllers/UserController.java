package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Classes.User;
import com.milesraker.milesahead.Classes.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping(path="/users")
    public String listUsers(Model model){
        model.addAttribute("users", userDao.findAll());
        return "/users/index";
    }

    @GetMapping(path="/users/create")
    public String createUser(){
        return "/users/create";
    }

    @PostMapping(path="/users/create")
    public String submitNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        User newUser = new User(username, email, password);
        userDao.save(newUser);
        return "redirect:/users";
    }
}
