package com.example.demo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserResource {

    //@TODO: Lazy atm, move this out at some point.
    @ApiModel(description = "User Resource - tells us everything about Users")
    class User {

        private String firstName;
        private String lastName;
        @Past
        @ApiModelProperty(notes="Birth date should be in the past")
        private Date birthDate;

        public User(String firstName, String lastName, Date birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
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

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }
    }

    @RequestMapping(path = "/getUsers")
    public List<User> getUsers() {
        final int[] i = {1};
        final List<User> some = Stream.generate(() -> {
            i[0] += 1;
            return new User(String.valueOf(i[0]), "adams", new Date());
        })
                .limit(5)
                .collect(Collectors.toList());
        return some;

    }
}
