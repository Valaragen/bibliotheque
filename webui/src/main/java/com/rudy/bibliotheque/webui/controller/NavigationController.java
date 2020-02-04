package com.rudy.bibliotheque.webui.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String getHomepage() {
        return "homePage";
    }

    @GetMapping("/books")
    public String getBooksPage() { return "books"; }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/loans")
    public String getLoansPage(HttpServletRequest request) {
        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String token = kp.getAccount().getKeycloakSecurityContext().getTokenString();
//        System.out.println(request.getHeader("Authorization"));
        return "loans";
    }
}
