package com.taxi.app.repository.security;

import com.taxi.app.entity.security.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * RoleRepository
 *
 * @author alankavanagh
 *
 * Role Repository used for database management of the Role entity
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(final String name);
}