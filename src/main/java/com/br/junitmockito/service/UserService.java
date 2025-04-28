package com.br.junitmockito.service;

import com.br.junitmockito.dto.UserDetailsDTO;

import java.util.List;

public interface UserService {

    List<UserDetailsDTO> findAll();

    UserDetailsDTO findById(Long id);

    boolean existsById(Long id);
}
