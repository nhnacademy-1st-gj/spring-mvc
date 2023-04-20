package com.academy.score.config;

import com.academy.score.Base;
import com.academy.score.repository.StudentRepository;
import com.academy.score.repository.StudentRepositoryImpl;
import com.academy.score.repository.UserRepository;
import com.academy.score.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public StudentRepository studentRepository() {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        studentRepository.register("Dave Grohl", "dave@grohl.com", 100, "American Rock Band the Foo Fighter's front man.");

        return studentRepository;
    }

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.register("admin@admin.com", "12345");

        return userRepository;
    }

}
