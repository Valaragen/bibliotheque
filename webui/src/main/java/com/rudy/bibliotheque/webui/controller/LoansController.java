package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.proxies.BookApiProxy;
import com.rudy.bibliotheque.webui.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constant.LOANS_PATH)
public class LoansController {

    private BookApiProxy bookApiProxy;

    @Autowired
    public LoansController(BookApiProxy bookApiProxy){
        this.bookApiProxy = bookApiProxy;
    }

    @PreAuthorize("hasRole('"+ Constant.STAFF_ROLE_NAME +"')")
    @GetMapping
    public String getLoansPage(Model model) {
        model.addAttribute("loans", bookApiProxy.getAllLoans());
        return Constant.LOANS_LIST_PAGE;
    }

    @PreAuthorize("hasRole('"+ Constant.USER_ROLE_NAME +"')")
    @GetMapping("/my-loans")
    public String getCurrentUserLoans(Model model) {
        model.addAttribute("loans", bookApiProxy.getLoansByCurrentUser());
        return Constant.LOANS_LIST_PAGE;
    }
}
