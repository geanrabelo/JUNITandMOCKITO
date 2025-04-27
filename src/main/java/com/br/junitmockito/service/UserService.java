package com.br.junitmockito.service;

import com.br.junitmockito.domain.User;

public interface UserService {

    User findById(Long id);

    boolean existsById(Long id);
}
