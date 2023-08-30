package com.desafio.pitang.pitang.service;

import com.desafio.pitang.pitang.entity.Car;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.model.UserUpdateDto;
import com.desafio.pitang.pitang.repository.CarRepository;
import com.desafio.pitang.pitang.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<UserPitang> getAllUser(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> save(UserCreateDto userDto){
        if(this.findByLogin(userDto.getLogin()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login already exists");
        if(this.findByEmail(userDto.getEmail()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        UserPitang userPitang = new UserPitang();
        userPitang = userRepository.save(userPitang.toEntity(userDto));
        if(userDto.getCars() != null) {
            List<Car> cars = userDto.toEntityCars(userDto.getCars(), userPitang);
            for (Car car : cars) {
                carRepository.save(car);
            }
        }
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    public UserDetails findByLogin(String login){
        return userRepository.findByLogin(login);
    }
    public UserPitang getByLogin(String login){
        return userRepository.getByLogin(login);
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
