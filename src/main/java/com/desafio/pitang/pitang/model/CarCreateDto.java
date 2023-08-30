package com.desafio.pitang.pitang.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class CarCreateDto {
    private Long year;
    @NotBlank(message = "not.blank")
    private String licensePlate;
    @NotBlank(message = "not.blank")
    private String model;
    @NotBlank(message = "not.blank")
    private String color;

}
