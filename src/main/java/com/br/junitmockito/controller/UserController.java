package com.br.junitmockito.controller;

import com.br.junitmockito.domain.User;
import com.br.junitmockito.dto.MessageDTO;
import com.br.junitmockito.dto.UserCreateDTO;
import com.br.junitmockito.dto.UserDetailsDTO;
import com.br.junitmockito.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> create(@RequestBody @Valid UserCreateDTO userCreateDTO){
        User user = userService.save(userCreateDTO);
        URI uri = UriComponentsBuilder.fromPath("/v1/user").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new MessageDTO("User registered successfully"));
    }

    @GetMapping("/id")
    public ResponseEntity<UserDetailsDTO> findById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
