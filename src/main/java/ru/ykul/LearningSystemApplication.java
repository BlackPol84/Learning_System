package ru.ykul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.ykul.repository")
public class LearningSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningSystemApplication.class, args);
    }
}
