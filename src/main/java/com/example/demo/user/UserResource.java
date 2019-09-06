package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserResource {
    @RequestMapping(path = "/getUsers")
    public List<User> getUsers() {
        final int[] i = {1};
        final List<User> some = Stream.generate(() -> {
            i[0] += 1;
            return new User("adams", new Date());
        })
                .limit(5)
                .collect(Collectors.toList());
        return some;

    }
}
