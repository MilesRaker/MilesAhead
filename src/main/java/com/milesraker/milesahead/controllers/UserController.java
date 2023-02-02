package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Models.User;
import com.milesraker.milesahead.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path="/users")
    public String listUsers(Model model){
        model.addAttribute("users", userDao.findAll());
        return "/users/index";
    }

    @GetMapping(path="/users/sign-up")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "/users/sign-up";
    }

    @PostMapping(path="/users/sign-up")
    public String submitNewUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping (path="/users/profile")
    public String individualUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/users/user";
    }

    @GetMapping(path="/users/profile/edit")
    public String editUser(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/users/createAndEdit";
    }

    @PostMapping(path="/users/edit")
    public String editUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/users";
    }

    @PostMapping(path="/users/delete")
    public String deleteUser(@RequestParam(name="userId") long id){
        User user = userDao.getReferenceById(id);
        userDao.delete(user);
        return "redirect:/users";
    }
}
