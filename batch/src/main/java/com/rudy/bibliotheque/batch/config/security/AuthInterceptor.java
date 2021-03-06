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
// TODO https://stackoverflow.com/questions/57974630/oauth2-client-credentials-flow-via-spring-boot-keycloak-integration
// TODO https://gist.github.com/thomasdarimont/52152ed68486c65b50a04fcf7bd9bbde
class AuthInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        //        User borrowingUser = borrow.getUser();
//        borrowDTO.setUserUsername(borrowingUser.getUsername());
//        borrowDTO.setUserEmail(borrowingUser.getEmail());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        Keycloak keycloak = KeycloakBuilder.builder()
//                .realm(Constant.REA)
//                .serverUrl(Constant.KE)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                .build();
        AuthzClient authzClient = AuthzClient.create();
        AuthorizationRequest request = new AuthorizationRequest();
        AuthorizationResponse response = authzClient.authorization("batch", "Azerty").authorize(request);
        String rpt = response.getToken();
        logger.info("You got an RPT");
        template.header("Authorization", "Bearer " + rpt);
    }

}
