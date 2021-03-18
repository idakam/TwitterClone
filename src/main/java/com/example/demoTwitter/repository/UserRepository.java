package com.example.demoTwitter.repository;

import com.example.demoTwitter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // here is the user repo to set the instructions to get the user by user name.
    User findByUsername(String username);
}