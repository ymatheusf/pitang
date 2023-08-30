package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.JWT.SecurityFilter;
import com.desafio.pitang.pitang.JWT.TokenService;
import com.desafio.pitang.pitang.model.CarCreateDto;
import com.desafio.pitang.pitang.model.CarUpdateDto;
import com.desafio.pitang.pitang.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    private CarController carController;

    @Mock
    private CarService carServiceMock;

    @Mock
    private SecurityFilter securityFilterMock;

    @Mock
    private TokenService tokenServiceMock;

    @Mock
    private HttpServletRequest requestMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        carController = new CarController();
        carController.carService = carServiceMock;
        carController.securityFilter = securityFilterMock;
        carController.tokenService = tokenServiceMock;
    }

    @Test
    public void testSave_ValidCar_ReturnsCreated() {
        CarCreateDto carDto = new CarCreateDto();

        when(securityFilterMock.recoverToken(requestMock)).thenReturn("valid_token");
        when(tokenServiceMock.validateToken("valid_token")).thenReturn("user_login");
        when(carServiceMock.save(any(CarCreateDto.class), eq("user_login"))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = carController.save(requestMock, carDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetAll_ReturnsCarsList() {
        when(securityFilterMock.recoverToken(requestMock)).thenReturn("valid_token");
        when(tokenServiceMock.validateToken("valid_token")).thenReturn("user_login");
        when(carServiceMock.getAllCarsUserPitangId("user_login")).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = carController.getAll(requestMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetById_ValidId_ReturnsCar() {
        when(securityFilterMock.recoverToken(requestMock)).thenReturn("valid_token");
        when(tokenServiceMock.validateToken("valid_token")).thenReturn("user_login");
        when(carServiceMock.getById(anyLong(), eq("user_login"))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = carController.getById(requestMock, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}