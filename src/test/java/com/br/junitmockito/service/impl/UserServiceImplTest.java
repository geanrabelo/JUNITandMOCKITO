package com.br.junitmockito.service.impl;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.dto.UserUpdateDTO;
import com.br.junitmockito.exceptions.ex.UserEmailAlreadyExists;
import com.br.junitmockito.exceptions.ex.UserNotFound;
import com.br.junitmockito.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private UserDetailsDTO userDetailsDTO;

    private UserCreateDTO userCreateDTO;

    private User user;

    private UserUpdateDTO userUpdateDTO;

    private List<UserDetailsDTO> userDetailsDTOList;

    private static final Long ID = 1L;
    private static final String NAME = "Gean";
    private static final String EMAIL = "gean@gmail.com";
    private static final String PASSWORD = "123";
    private static final String MESSAGE_USER_NOT_FOUND = "User find by id not found";
    private static final String MESSAGE_USER_ALREADY_EXISTS_EMAIL = "Email of user already exists in database. Try again";

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);

        User response = userServiceImpl.save(userCreateDTO);

        Assertions.assertEquals(User.class, response.getClass());
    }

    @Test
    void whenSaveThenReturnUserEmailAlreadyExists(){
        Mockito.when(userRepository
                .existsByEmail(Mockito.any()))
                .thenThrow(new UserEmailAlreadyExists(MESSAGE_USER_ALREADY_EXISTS_EMAIL));

        try{

            userServiceImpl.save(userCreateDTO);

        }catch (Exception ex){
            Assertions.assertEquals(UserEmailAlreadyExists.class, ex.getClass());
            Assertions.assertEquals(MESSAGE_USER_ALREADY_EXISTS_EMAIL, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);
        Mockito.when(userRepository.getReferenceById(Mockito.any())).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User response = userServiceImpl.update(userUpdateDTO);

        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(PASSWORD, response.getPassword());
        Assertions.assertEquals(ID, response.getId());
    }

    @Test
    void whenUpdateThenReturnFailed(){
        Mockito.when(userRepository.existsById(Mockito.any())).thenThrow(new UserNotFound(MESSAGE_USER_NOT_FOUND));

        try{

            userServiceImpl.update(userUpdateDTO);

        }catch (Exception ex){

            Assertions.assertEquals(UserNotFound.class, ex.getClass());
            Assertions.assertEquals(MESSAGE_USER_NOT_FOUND, ex.getMessage());

        }
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
        Mockito.when(userRepository.findById(ID)).thenThrow(new UserNotFound(MESSAGE_USER_NOT_FOUND));

        try{
            userServiceImpl.findById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(UserNotFound.class, ex.getClass());
            Assertions.assertEquals(MESSAGE_USER_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(userRepository).deleteById(Mockito.any());

        userServiceImpl.deleteById(ID);
    }

    @Test
    void deleteWithoutSuccess(){
        Mockito.when(userRepository.existsById(Mockito.any())).thenThrow(new UserNotFound(MESSAGE_USER_NOT_FOUND));

        try {
            userServiceImpl.deleteById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(UserNotFound.class, ex.getClass());
        }
    }

    @Test
    void whenFindAllThenReturnListOfUserDetailsDTO() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDetailsDTO> response = userServiceImpl.findAll();

        Assertions.assertEquals(userDetailsDTOList, response);
        Assertions.assertEquals(UserDetailsDTO.class, response.get(0).getClass());
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
        userCreateDTO = new UserCreateDTO(NAME, EMAIL, PASSWORD);
    }
}