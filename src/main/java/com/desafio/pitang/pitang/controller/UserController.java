package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.PitangApplication;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.model.UserUpdateDto;
import com.desafio.pitang.pitang.repository.UserRepository;
import com.desafio.pitang.pitang.service.UserService;
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



    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserCreateDto user){
        try{
            if(userService.findByLogin(user.getLogin()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login already exists");
            if(userService.findByEmail(user.getEmail()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage().toString());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UserPitang> list = userService.getAllUser();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userService.getById(id), HttpStatus.CREATED);
        }catch(Exception e){

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            userService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deletado com Sucesso");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,
                                        @RequestBody UserUpdateDto request){
        try{
            userService.updateUserById(id, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Alterado com Sucesso!");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

}
