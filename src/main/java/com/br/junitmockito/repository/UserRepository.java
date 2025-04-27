package com.br.junitmockito.repository;

import com.br.junitmockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
