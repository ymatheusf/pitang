package com.desafio.pitang.pitang.service;

import com.desafio.pitang.pitang.entity.Car;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.CarCreateDto;
import com.desafio.pitang.pitang.model.UserReturnDto;
import com.desafio.pitang.pitang.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Test
    public void testToEntity_ConvertsCarDtoToEntity() {
        CarService carService = new CarService();

        CarCreateDto carDto = new CarCreateDto();
        carDto.setYear(2023L);
        carDto.setColor("Red");
        UserPitang user = new UserPitang();

        Car car = carService.toEntity(carDto, user);

        assertNotNull(car);
        assertEquals(2023, car.getAno());
        assertEquals("Red", car.getColor());

    }

    @Test
    public void testGetAllCarsUserPitangId_NoCars_ReturnsNoContent() {
        CarRepository carRepositoryMock = Mockito.mock(CarRepository.class);
        UserService userServiceMock = Mockito.mock(UserService.class);
        CarService carService = new CarService();
        carService.setCarRepository(carRepositoryMock);
        carService.setUserService(userServiceMock);

        when(userServiceMock.getByLogin("testuser")).thenReturn(new UserPitang());
        when(carRepositoryMock.getByUserPitangId(anyLong())).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = carService.getAllCarsUserPitangId("testuser");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}