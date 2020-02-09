package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.proxies.BookApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookApiProxy bookApiProxy;

    @Autowired
    public BooksController(BookApiProxy bookApiProxy){
        this.bookApiProxy = bookApiProxy;
    }

    @GetMapping
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookApiProxy.getAllBooks());
        return "books";
    }
}
