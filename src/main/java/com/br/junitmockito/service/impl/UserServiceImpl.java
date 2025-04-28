package com.br.junitmockito.service.impl;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserDetailsDTO;
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
}
