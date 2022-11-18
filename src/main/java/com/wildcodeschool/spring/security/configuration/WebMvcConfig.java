package com.wildcodeschool.spring.security.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.spring.security.persistence.entities.User;
import com.wildcodeschool.spring.security.persistence.enums.RoleEnum;
import com.wildcodeschool.spring.security.persistence.repositories.UserRepository;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Autowired
    public WebMvcConfig(UserRepository userRepository) {
        // Ceci n'est pas à recopier en production
        List<RoleEnum> userRole = Collections.singletonList(RoleEnum.USER);
        List<RoleEnum> adminRole = Arrays.asList(RoleEnum.USER, RoleEnum.ADMINISTRATOR);
        User user = new User("user", "user", "User", "USER", userRole);
        User adminUser = new User("admin", "admin", "Admin", "ADMIN", adminRole);
        userRepository.save(user);
        userRepository.save(adminUser);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/auth").setViewName("auth/auth");
        registry.addViewController("/auth/admin").setViewName("auth/admin");
        registry.addViewController("/errorAlreadyConnected").setViewName("/errorAlreadyConnected");
        registry.addViewController("/errorAccessUnAuthorised").setViewName("errorAccessUnAuthorised");
        registry.addViewController("/error").setViewName("error");
    }
}