package com.br.junitmockito.service;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;

import java.util.List;

public interface UserService {

    User save(UserCreateDTO userCreateDTO);

    List<UserDetailsDTO> findAll();

    UserDetailsDTO findById(Long id);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
