package com.br.junitmockito.service.impl;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.repository.UserRepository;
import com.br.junitmockito.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
