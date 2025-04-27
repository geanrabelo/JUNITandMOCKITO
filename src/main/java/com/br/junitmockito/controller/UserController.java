package com.br.junitmockito.controller;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> findById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }
}
