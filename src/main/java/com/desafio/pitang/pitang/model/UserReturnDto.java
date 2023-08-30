package com.desafio.pitang.pitang.model;

import com.desafio.pitang.pitang.entity.Car;

import java.util.Date;
import java.util.List;

public record UserReturnDto(
        String fistName,
        String lastName,
        String email,
        Date birthday,
        String login,
        String phone,
        List<Car> cars,
        Date createAt,
        Date lastLogin
) {
}