package com.milesraker.milesahead.controllers;

import com.milesraker.milesahead.Classes.Post;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String posts(Model model) {
        Post post1 = new Post(1, 1, "Too Many Cats!", "Please take some of these cats. There are so so many. I really need to get these cute kittens into new homes.");
        Post post2 = new Post(2, 1, "Not Enough Lizards", "Send me Lizards! Trade lizards for cats? I like the beareded ones.");
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individualPost(Model model) {
        Post post1 = new Post(1, 1, "Too Many Cats!", "Please take some of these cats. There are so so many. I really need to get these cute kittens into new homes.");
        model.addAttribute("post", post1);
        return "/posts/post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String postCreate() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String hello() {
        return "create a new post";
    }
}
