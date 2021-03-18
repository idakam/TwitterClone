package com.example.demoTwitter.repository;

import com.example.demoTwitter.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    // the role repo is setting up a interface to give instructions for the program to follow.
    // Here we are getting the user by the role.
    Role findByRole(String role);
}