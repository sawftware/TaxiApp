package com.taxi.app.service.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(final String username, final String password);
}