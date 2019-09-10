package com.example.demo.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserJPAResource {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJPAResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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

    @DeleteMapping(path="/jpa/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        this.userRepository.deleteById(id);
    }

    @PostMapping(path="/jpa/users")
    public User createUser(@Valid @RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id) throws Exception {
        final Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new Exception("id-"  + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("jpa/users/{id}/posts")
    public Post createPost(@PathVariable int id, @RequestBody Post post) throws Exception {
        final Optional<User> userOption = userRepository.findById(id);

        if(!userOption.isPresent()) {
            throw new Exception("id- " + id);
        }

        final User user = userOption.get();
        post.setUser(user);
        return postRepository.save(post);
    }
}
