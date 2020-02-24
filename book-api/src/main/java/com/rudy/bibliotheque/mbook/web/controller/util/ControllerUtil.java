package com.rudy.bibliotheque.mbook.web.controller.util;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class ControllerUtil {
    public static String getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) authentication;
        return kp.getAccount().getKeycloakSecurityContext().getToken().getSubject();
    }
}
