package com.br.junitmockito.dto;

import com.br.junitmockito.domain.User;

public record UserDetailsDTO(Long id,
                             String name,
                             String email,
                             String password) {
    public UserDetailsDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
