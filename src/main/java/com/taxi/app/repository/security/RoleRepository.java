package com.taxi.app.repository.security;

import com.taxi.app.entity.security.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(final String name);
}