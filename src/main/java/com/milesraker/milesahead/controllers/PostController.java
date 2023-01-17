package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Classes.Post;
import com.milesraker.milesahead.Classes.PostRepository;
import com.milesraker.milesahead.Classes.User;
import com.milesraker.milesahead.Classes.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
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
    public String listPosts() {
        return "/posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String submitNewPost(@RequestParam String title, @RequestParam String description, @RequestParam int posterId) {
        Post newPost = new Post(posterId, title, description, userDao.findById(posterId));
        postDao.save(newPost);
        return "redirect:/posts";
    }



}
