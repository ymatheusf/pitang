package com.desafio.pitang.pitang.service;

import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.model.UserUpdateDto;
import com.desafio.pitang.pitang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<UserPitang> getAllUser(){
        return userRepository.findAll();
    }

    public UserPitang save(UserCreateDto userDto){
        UserPitang userPitang = new UserPitang();
        return userRepository.save(userPitang.toEntity(userDto));
    }
    public UserDetails findByLogin(String login){
        return userRepository.findByLogin(login);
    }
    public UserPitang findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<UserPitang> getById(Long id){
        return userRepository.findById(id);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public void updateUserById(Long id, UserUpdateDto request){
        UserPitang userPitang = userRepository.findById(id).get();
        userPitang = request.toEntity(userPitang,request);
        userRepository.save(userPitang);
    }
    public void updateLastLogin(Long id){
        UserPitang user = userRepository.findById(id).get();
        user.setLastLogin(Date.from(Instant.now()));
        userRepository.save(user);
    }
}
