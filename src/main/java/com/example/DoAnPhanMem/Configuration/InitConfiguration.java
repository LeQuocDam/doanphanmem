package com.example.DoAnPhanMem.Configuration;

import com.example.DoAnPhanMem.Entity.User;
import com.example.DoAnPhanMem.Repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitConfiguration {

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUserName("Admin").isEmpty()){
                User user = new User();
                user.setUserName("Admin");
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                user.setPassword(passwordEncoder.encode("DoAnPhanMem"));
                user.setRole("Manager");
                userRepository.save(user);
            }
        };
    }
}
