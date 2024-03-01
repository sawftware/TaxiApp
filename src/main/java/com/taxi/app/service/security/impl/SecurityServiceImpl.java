package com.taxi.app.service.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.taxi.app.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * SecurityServiceImpl
 *
 * @author alankavanagh
 *
 * Implementation of the SecurityService interface
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void autologin(final String username, final String password) {
        logger.debug("SecurityServiceImpl: Executing autologin()");

        logger.debug("SecurityServiceImpl: Attempting to auto-login user: " + username);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug("SecurityServiceImpl: Auto-login " + username + " successful!");
        }
    }
}
