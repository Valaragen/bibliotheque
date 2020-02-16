package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.dto.BookSearchDTO;
import com.rudy.bibliotheque.webui.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping
    public String getHomepage(Model model) {
        model.addAttribute("bookSearch", new BookSearchDTO());
        return Constant.HOME_PAGE;
    }
}
