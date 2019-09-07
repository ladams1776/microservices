package com.example.demo.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        return this.userRepository.findAll();
    }

    @RequestMapping(path = "/jpa/user/{id}")
    public User retrieveAllUser(@PathVariable int id) throws Exception {
        final Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()) {
            throw new Exception("id-"+id);
        }
        return user.get();
    }

}
