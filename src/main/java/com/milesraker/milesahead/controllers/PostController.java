package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Classes.Post;
import com.milesraker.milesahead.Classes.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;

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
        postDao.save(post);
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
