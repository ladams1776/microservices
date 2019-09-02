package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserResource {

    //@TODO: Lazy atm, move this out at some point.
    class User {
        private String firstName;
        private String lastName;

        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @RequestMapping(path = "/getUsers")
    public List<User> getUsers() {
        final int[] i = {1};
        final List<User> some = Stream.generate(() -> {
            i[0] += 1;
            return new User(String.valueOf(i[0]), "adams");
        })
                .limit(5)
                .collect(Collectors.toList());
        return some;

    }
}
