package com.br.junitmockito.service.impl;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.exceptions.ex.UserEmailAlreadyExists;
import com.br.junitmockito.exceptions.ex.UserNotFound;
import com.br.junitmockito.repository.UserRepository;
import com.br.junitmockito.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserCreateDTO userCreateDTO) {
        if(!existsByEmail(userCreateDTO.email())){
            User user = User.builder()
                    .email(userCreateDTO.email())
                    .name(userCreateDTO.name())
                    .password(userCreateDTO.password())
                    .build();
            userRepository.save(user);
            return user;
        }
        throw new UserEmailAlreadyExists("Email of user already exists in database. Try again");
    }

    @Override
    public UserDetailsDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User find by id not found"));
        return new UserDetailsDTO(user);
    }


    @Override
    public List<UserDetailsDTO> findAll() {
        return userRepository.findAll().stream().map(UserDetailsDTO::new).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
