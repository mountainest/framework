package io.github.mountainest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// springfox
@EnableWebMvc
@MapperScan("io.github.mountainest.mapper")
@SpringBootApplication
public class FriendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication.class, args);
    }
}
