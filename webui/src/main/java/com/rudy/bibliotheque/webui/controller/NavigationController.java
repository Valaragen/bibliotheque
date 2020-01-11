package com.rudy.bibliotheque.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String getHomepage() {
        return "homepage";
    }
}
