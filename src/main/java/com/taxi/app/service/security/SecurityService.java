package com.taxi.app.service.security;

/**
 * SecurityService
 *
 * @author alankavanagh
 *
 * Defines the SecurityService interface
 */
public interface SecurityService {
    void autologin(final String username, final String password);
}