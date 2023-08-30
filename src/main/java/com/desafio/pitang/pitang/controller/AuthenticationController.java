package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.JWT.TokenService;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.AuthenticationDTO;
import com.desafio.pitang.pitang.model.LoginResponseDTO;
import com.desafio.pitang.pitang.repository.UserRepository;
import com.desafio.pitang.pitang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signin")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((UserPitang) auth.getPrincipal());
            userService.updateLastLogin(((UserPitang) auth.getPrincipal()).getId());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Invalid login or password");
        }
    }
}
