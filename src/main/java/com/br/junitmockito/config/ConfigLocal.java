package com.br.junitmockito.config;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class ConfigLocal {

    private final UserRepository userRepository;

    public ConfigLocal(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public String startDB(){
        User u1 = new User(null, "Gean", "gean@gmail.com", "123");
        User u2 = new User(null, "Marcos", "marcos@gmail.com", "123");
        userRepository.save(u1);
        userRepository.save(u2);
        return "Ok";
    }
}
