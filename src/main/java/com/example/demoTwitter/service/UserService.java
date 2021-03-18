package com.example.demoTwitter.service;

import com.example.demoTwitter.model.Role;
import com.example.demoTwitter.model.User;
import com.example.demoTwitter.repository.RoleRepository;
import com.example.demoTwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
// the service file is the middle man for the controller and the repo. the service file controls what the data base does and the actions.
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    // Here is the findByUser that locates the user in the database by the user name.
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    // this find all the users
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    // saving the users to the database.
    public void save(User user) {
        userRepository.save(user);
    }
    // saving the new user to the database and encoding the password
    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    // checking which user is logged in.
    public User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.
                getContext().getAuthentication().getName();

        return findByUsername(loggedInUsername);
    }
}