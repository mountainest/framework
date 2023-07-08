package io.github.mountainest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// springfox
@EnableWebMvc
@SpringBootApplication
public class FriendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication.class, args);
    }
}
