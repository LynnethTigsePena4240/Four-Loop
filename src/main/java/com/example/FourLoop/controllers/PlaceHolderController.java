package com.example.FourLoop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceHolderController {
    @GetMapping("/placeHolder")
    public String getPlaceHolderPage(Model model){
        model.addAttribute("message","Place holder Title");
        return "PlaceHolder";
    }
}
