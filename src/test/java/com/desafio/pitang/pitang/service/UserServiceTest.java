package com.desafio.pitang.pitang.service;

import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.model.UserUpdateDto;
import com.desafio.pitang.pitang.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepositoryMock =  Mockito.mock(UserRepository.class);

    private UserService userService = new UserService();


    @Test
    public void testSave_NewUser_Success() throws Exception {
        userService.setUserRepository(userRepositoryMock);

        UserCreateDto newUserDto = new UserCreateDto();
        newUserDto.setLogin("newuser");
        newUserDto.setEmail("newuser@example.com");

        when(userRepositoryMock.findByLogin("newuser")).thenReturn(null);
        when(userRepositoryMock.findByEmail("newuser@example.com")).thenReturn(null);
        when(userRepositoryMock.save(any(UserPitang.class))).thenReturn(new UserPitang());

        ResponseEntity<?> response = userService.save(newUserDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testFindByLogin_UserExists_ReturnUserDetails() {
        userService.setUserRepository(userRepositoryMock);

        when(userRepositoryMock.findByLogin("existinguser")).thenReturn(new UserPitang());

        UserDetails userDetails = userService.findByLogin("existinguser");

        assertNotNull(userDetails);
    }

    @Test
    public void testUpdateUserById_ValidId_Success() {
        userService.setUserRepository(userRepositoryMock);

        Long userId = 1L;
        UserUpdateDto updateDto = new UserUpdateDto();
        // Set properties in the updateDto

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(new UserPitang()));
        when(userRepositoryMock.save(any(UserPitang.class))).thenReturn(new UserPitang());

        assertDoesNotThrow(() -> userService.updateUserById(userId, updateDto));
    }
}
