package com.br.junitmockito.service.impl;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.dto.UserUpdateDTO;
import com.br.junitmockito.exceptions.ex.UserNotFound;
import com.br.junitmockito.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private UserDetailsDTO userDetailsDTO;

    private User user;

    private UserUpdateDTO userUpdateDTO;

    private List<UserDetailsDTO> userDetailsDTOList;

    private static final Long ID = 1L;
    private static final String NAME = "Gean";
    private static final String EMAIL = "gean@gmail.com";
    private static final String PASSWORD = "123";

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        UserDetailsDTO response = userServiceImpl.findById(ID);

        Assertions.assertEquals(UserDetailsDTO.class, response.getClass());
        Assertions.assertEquals(EMAIL, response.email());
        Assertions.assertEquals(ID, response.id());
        Assertions.assertEquals(NAME, response.name());
    }

    @Test
    void whenFindByIdThenReturnUserNotFoundHandler(){
        Mockito.when(userRepository.findById(ID)).thenThrow(new UserNotFound("User find by id not found"));

        try{
            userServiceImpl.findById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(UserNotFound.class, ex.getClass());
            Assertions.assertEquals("User find by id not found", ex.getMessage());
        }
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAll() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDetailsDTO> response = userServiceImpl.findAll();

        Assertions.assertEquals(userDetailsDTOList, response);
    }

    @Test
    void existsById() {
    }

    @Test
    void existsByEmail() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDetailsDTO = new UserDetailsDTO(ID, NAME, EMAIL);
        userUpdateDTO = new UserUpdateDTO(ID, EMAIL);
        userDetailsDTOList = List.of(new UserDetailsDTO(user));
    }
}