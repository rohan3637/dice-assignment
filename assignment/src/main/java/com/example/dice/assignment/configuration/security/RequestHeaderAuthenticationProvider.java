package com.example.dice.assignment.configuration.security;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RequestHeaderAuthenticationProvider implements AuthenticationProvider {

    @Value("${api.client.secret}")
    private String apiAuthSecret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authSecretKey = String.valueOf(authentication.getPrincipal());
        if(StringUtils.isBlank(authSecretKey) || !authSecretKey.equals(apiAuthSecret)) {
            throw new BadCredentialsException("Bad Request Header Credentials");
        }
        return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(), null, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
