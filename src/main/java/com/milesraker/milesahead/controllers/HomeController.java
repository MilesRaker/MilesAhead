package com.milesraker.milesahead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! This is the landing page!";
    }
}
