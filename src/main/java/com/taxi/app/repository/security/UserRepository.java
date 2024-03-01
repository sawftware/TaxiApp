package com.taxi.app.repository.security;

import com.taxi.app.entity.security.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(final String username);
}