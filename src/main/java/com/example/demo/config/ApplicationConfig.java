package com.example.demo.config;


import com.example.demo.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AdminRepository repository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}