package com.taxi.app.service.security;

import com.taxi.app.entity.security.User;

/**
 * UserService
 *
 * @author alankavanagh
 *
 * Defines the UserService interface
 */
public interface UserService {
    void save(final User user);
}