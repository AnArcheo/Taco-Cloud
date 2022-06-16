package com.springinaction.tacocloud.config;


import com.springinaction.tacocloud.domains.User;
import com.springinaction.tacocloud.repositories.UserRepository;
import com.springinaction.tacocloud.services.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if(user != null){
                return user;
            }
            throw new UsernameNotFoundException("User '"+ username + "' not found");
        };
    }
}
