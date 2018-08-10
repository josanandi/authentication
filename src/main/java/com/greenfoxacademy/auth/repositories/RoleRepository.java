package com.greenfoxacademy.auth.repositories;

import com.greenfoxacademy.auth.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String role_admin);
}
