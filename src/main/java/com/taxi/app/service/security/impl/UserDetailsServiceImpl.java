package com.taxi.app.service.security.impl;

import java.util.Set;
import org.slf4j.Logger;
import java.util.HashSet;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;
import com.taxi.app.entity.security.Role;
import com.taxi.app.entity.security.User;
import org.springframework.stereotype.Service;
import com.taxi.app.repository.security.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsServiceImpl
 *
 * @author alankavanagh
 *
 * Implementation of the UserDetailsService interface
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        logger.debug("UserDetailsServiceImpl: Executing loadUserByUsername()");

        logger.debug("UserDetailsServiceImpl: Loading user: " + username);
        final User user = userRepository.findByUsername(username);
        logger.debug("UserDetailsServiceImpl: Loaded user: " + user);

        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (final Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        logger.debug("UserDetailsServiceImpl: Assigning roles: " + grantedAuthorities);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
