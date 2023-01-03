package com.milesraker.milesahead.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @RequestMapping(path = "/rollDice/{number}", method = RequestMethod.GET)
    @GetMapping("/rollDice/{number}")
    public String add(@PathVariable int number, Model model) {
        int diceRoll = (int) (Math.floor(Math.random() * 6) + 1);
        model.addAttribute("guess", number);
        model.addAttribute("randNum", diceRoll);
        if(diceRoll == number){
            model.addAttribute("match", true);
        } else {
            model.addAttribute("match", false);
        }
        return "rollDice";
    }
}
