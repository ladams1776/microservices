package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserJPAResource {
    private UserRepository userRepository;

    public UserJPAResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }
}
