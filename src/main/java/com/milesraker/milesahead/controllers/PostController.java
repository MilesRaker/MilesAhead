package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Models.*;
import com.milesraker.milesahead.repositories.PostRepository;
import com.milesraker.milesahead.repositories.UserRepository;
import com.milesraker.milesahead.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final EmailService emailService;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, EmailService emailService, UserRepository userDao) {
        this.postDao = postDao;
        this.emailService = emailService;
        this.userDao = userDao;
        }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(Model model, @PathVariable long id) {
        model.addAttribute("post", postDao.findById(id));
        return "/posts/post";
    }

    @GetMapping(path = "/posts/create")
    public String listPosts(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/createAndEdit";
    }

    @PostMapping(path = "/posts/create")
    public String create(@ModelAttribute("post") Post post) {
        User user = userDao.getReferenceById(1L);
        post.setUser(user);
        postDao.save(post);
        // send email here
        emailService.prepareAndSend(post, "New Post Created", "A new post has been created with the title: " + post.getTitle());
        return "redirect:/posts";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String editPost(Model model, @PathVariable long id) {

        model.addAttribute("post", postDao.getReferenceById(id));
        return "posts/createAndEdit";
    }

    @PostMapping("/posts/edit")
    public String edit(@ModelAttribute Post post) {

        postDao.save(post);
        return "redirect:/posts";

    }
}
