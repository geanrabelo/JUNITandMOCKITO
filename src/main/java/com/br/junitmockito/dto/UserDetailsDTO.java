package com.br.junitmockito.dto;

import com.br.junitmockito.domain.User;

public record UserDetailsDTO(Long id,
                             String name,
                             String email) {
    public UserDetailsDTO(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
