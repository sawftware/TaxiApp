package com.taxi.app.config.handlers;

import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * LoginSuccessHandler
 *
 * @author alankavanagh
 *
 * Handler used for login that redirects to the index landing page
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    private static final String LANDING_REDIRECT_PAGE = "/";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Authentication authentication) throws IOException {
        logger.debug("LoginSuccessHandler: Executing onAuthenticationSuccess()");

        httpServletResponse.sendRedirect(LANDING_REDIRECT_PAGE);
    }
}