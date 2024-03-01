package com.taxi.app.repository.security;

import com.taxi.app.entity.security.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 *
 * @author alankavanagh
 *
 * User Repository used for database management of the User entity
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(final String username);
}