package com.rudy.bibliotheque.batch.config.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//TODO The token may not be correctly refreshed as batch are asyncronous ?
// TODO https://www.keycloak.org/docs/latest/server_admin/index.html DIRECT GRANT
class AuthInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        AuthzClient authzClient = AuthzClient.create();
        AuthorizationRequest request = new AuthorizationRequest();
        AuthorizationResponse response = authzClient.authorization("batch", "Azerty").authorize(request);
        String rpt = response.getToken();
        logger.info("You got an RPT");
        template.header("Authorization", "Bearer " + rpt);
    }

}
