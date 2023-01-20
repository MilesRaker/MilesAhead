package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Models.User;
import com.milesraker.milesahead.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "/users/createAndEdit";
    }

    @PostMapping(path="/users/createAndEdit")
    public String submitNewUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping (path="/users/{id}")
    public String individualUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userDao.findById(id));
        return "/users/user";
    }

    @GetMapping(path="/users/{id}/edit")
    public String editUser(Model model, @PathVariable long id){
        model.addAttribute("user", userDao.findById(id));
        return "/users/createAndEdit";
    }

    @PostMapping(path="/users/edit")
    public String editUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/users";
    }
}
