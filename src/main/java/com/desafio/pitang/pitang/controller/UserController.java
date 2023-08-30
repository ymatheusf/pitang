package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.JWT.SecurityFilter;
import com.desafio.pitang.pitang.JWT.TokenService;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.model.UserUpdateDto;
import com.desafio.pitang.pitang.service.CarService;
import com.desafio.pitang.pitang.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    TokenService tokenService;

    @Autowired
    CarService carService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserCreateDto user) {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userService.save(user);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error ao Criar Usuário ");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserPitang> list = userService.getAllUser();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.CREATED);
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deletado com Sucesso");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,
                                        @RequestBody UserUpdateDto request) {
        try {
            userService.updateUserById(id, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Alterado com Sucesso!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }


    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request) {
        var token = securityFilter.recoverToken(request);
        if (token != null) {
            var login = tokenService.validateToken(token);
            return new ResponseEntity<>(carService.toDto(userService.getByLogin(login)), HttpStatus.OK);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("invalid session");


    }

}
