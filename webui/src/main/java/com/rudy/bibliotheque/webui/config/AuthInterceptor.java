package com.rudy.bibliotheque.webui.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class AuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) authentication;
            String token = kp.getAccount().getKeycloakSecurityContext().getTokenString();
            template.header("Authorization", "Bearer " + token);
        }
    }

}
