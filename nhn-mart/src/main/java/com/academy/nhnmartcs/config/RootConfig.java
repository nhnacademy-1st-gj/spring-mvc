package com.academy.nhnmartcs.config;

import com.academy.nhnmartcs.Base;
import com.academy.nhnmartcs.domain.Authorization;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.InquiryRepository;
import com.academy.nhnmartcs.repository.InquiryRepositoryImpl;
import com.academy.nhnmartcs.repository.UserRepository;
import com.academy.nhnmartcs.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.getUserMap().put("user1", new User("user1", "12345", "Smith", Authorization.CUSTOMER));
        userRepository.getUserMap().put("user2", new User("user2", "12345", "John", Authorization.CUSTOMER));
        userRepository.getUserMap().put("admin", new User("admin", "123", "Admin", Authorization.ADMIN));

        return userRepository;
    }

    @Bean
    public InquiryRepository inquiryRepository() {
        return new InquiryRepositoryImpl();
    }
}
