package com.br.junitmockito.controller;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.dto.UserUpdateDTO;
import com.br.junitmockito.repository.UserRepository;
import com.br.junitmockito.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User user;

    private UserCreateDTO userCreateDTO;

    private UserDetailsDTO userDetailsDTO;

    private UserUpdateDTO userUpdateDTO;

    private List<UserDetailsDTO> userDetailsDTOList;

    private static final Long ID = 1L;
    private static final String NAME = "Gean";
    private static final String EMAIL = "gean@gmail.com";
    private static final String PASSWORD = "123";


    @BeforeEach
    void startUp(){
        startUser();
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void whenFindByIdThenReturnUserDetailsDTO() {
        Mockito.when(userController.findById(Mockito.any())).thenReturn(ResponseEntity.of(userDetailsDTO));
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDetailsDTO = new UserDetailsDTO(ID, NAME, EMAIL);
        userUpdateDTO = new UserUpdateDTO(ID, EMAIL);
        userDetailsDTOList = List.of(new UserDetailsDTO(user));
        userCreateDTO = new UserCreateDTO(NAME, EMAIL, PASSWORD);
    }
}