package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * experimenting with property filtering
 */
@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public List<SomeBean> retrieveSomeBean() {
        return asList(
                new SomeBean("value1", "value2", "value2"),
                new SomeBean("value2", "value3", "value4")
        );

    }
}
