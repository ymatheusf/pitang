package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.JWT.SecurityFilter;
import com.desafio.pitang.pitang.JWT.TokenService;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.UserCreateDto;
import com.desafio.pitang.pitang.service.CarService;
import com.desafio.pitang.pitang.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userServiceMock;

    @Mock
    private SecurityFilter securityFilterMock;

    @Mock
    private TokenService tokenServiceMock;

    @Mock
    private CarService carServiceMock;

    @Mock
    private HttpServletRequest requestMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController();
        userController.userService = userServiceMock;
        userController.securityFilter = securityFilterMock;
        userController.tokenService = tokenServiceMock;
        userController.carService = carServiceMock;
    }

    @Test
    public void testGetAllUsers_ReturnsUsersList() throws Exception {
        List<UserPitang> usersList = new ArrayList<>();
        usersList.add(new UserPitang());

        when(userServiceMock.getAllUser()).thenReturn(usersList);

        ResponseEntity<?> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersList, response.getBody());
    }

    @Test
    public void testGetAllUsers_NoUsers_ReturnsNoContent() throws Exception {
        when(userServiceMock.getAllUser()).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = userController.getAllUsers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // Similar tests for other UserController methods
}
