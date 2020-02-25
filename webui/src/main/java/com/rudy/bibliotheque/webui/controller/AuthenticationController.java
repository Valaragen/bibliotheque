package com.rudy.bibliotheque.webui.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class AuthenticationController {

    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realmName;

    @Value("${keycloak.resource}")
    private String clientName;

//    //TODO try a request with the KeycloakTemplate to get the cookie
//    @GetMapping("/register")
//    public String getRegistrationPage(HttpServletRequest request) throws UnsupportedEncodingException {
//        //th:href="@{{domain}/realms/{realm}/protocol/openid-connect/registrations?client_id={client}&response_type=code&scope=openid&redirect_uri={redirectUri}&kc_locale={locale}&login=true(domain = ${@environment.getProperty('keycloak.auth-server-url')},realm = ${@environment.getProperty('keycloak.realm')}, client = ${@environment.getProperty('keycloak.resource')}, redirectUri = ${#response.encodeRedirectURL('http://' + #httpServletRequest.getServerName() + ':' + #httpServletRequest.getServerPort() + '/sso/login')}, locale = ${#locale})}"
//        //redirect url
//        String encodedRedirectUrl = URLEncoder.encode("http://" + request.getServerName() + ":" + request.getServerPort() + "/sso/login", "UTF-8");
//        //state
//        String state = "test";
//
//        return "redirect:" + authUrl + "/realms/" + realmName + "/protocol/openid-connect/registrations?client_id=" + clientName + "&response_type=code&scope=openid&redirect_uri=" + encodedRedirectUrl + "&kc_locale=" + request.getLocale() + "&state=" + state;
//    }
}
