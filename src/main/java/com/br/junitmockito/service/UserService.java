package com.br.junitmockito.service;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {

    User save(UserCreateDTO userCreateDTO);

    User update(UserUpdateDTO userUpdateDTO);

    List<UserDetailsDTO> findAll();

    UserDetailsDTO findById(Long id);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);
}
