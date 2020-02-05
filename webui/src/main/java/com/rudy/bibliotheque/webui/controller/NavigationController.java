package com.rudy.bibliotheque.webui.controller;

import com.rudy.bibliotheque.webui.proxies.BookApiProxy;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NavigationController {

    private BookApiProxy bookApiProxy;

    @Autowired
    public NavigationController(BookApiProxy bookApiProxy){
        this.bookApiProxy = bookApiProxy;
    }

    @GetMapping("/")
    public String getHomepage() {
        return "homePage";
    }

    @GetMapping("/books")
    public String getBooksPage(Model model) {
        model.addAttribute("books", bookApiProxy.getAllBooks());
        return "books";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/loans")
    public String getLoansPage(HttpServletRequest request, Model model) {
//        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) request.getUserPrincipal();
//        String token = kp.getAccount().getKeycloakSecurityContext().getTokenString();
//        System.out.println(request.getHeader("Authorization"));
        model.addAttribute("loans", bookApiProxy.getAllLoans());
        return "loans";
    }
}
