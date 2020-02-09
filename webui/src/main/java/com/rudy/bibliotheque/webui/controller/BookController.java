package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.proxies.BookApiProxy;
import com.rudy.bibliotheque.webui.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.BOOKS_PATH)
public class BookController {

    private BookApiProxy bookApiProxy;

    @Autowired
    public BookController(BookApiProxy bookApiProxy){
        this.bookApiProxy = bookApiProxy;
    }

    @GetMapping
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookApiProxy.getAllBooks());
        return Constant.BOOKS_LIST_PAGE;
    }

    @GetMapping(Constant.SLASH_ID_PATH)
    public String getBookDetailsPageById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookApiProxy.getBookById(id));
        return Constant.BOOK_DETAILS_PAGE;
    }
}
