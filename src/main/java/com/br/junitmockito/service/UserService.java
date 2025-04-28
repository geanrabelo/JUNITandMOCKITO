package com.br.junitmockito.service;

import com.br.junitmockito.dto.UserDetailsDTO;

public interface UserService {

    UserDetailsDTO findById(Long id);

    boolean existsById(Long id);
}
