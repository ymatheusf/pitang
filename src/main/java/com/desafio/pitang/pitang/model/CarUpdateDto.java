package com.desafio.pitang.pitang.model;

import com.desafio.pitang.pitang.entity.Car;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarUpdateDto {
    private Long year;
    private String licensePlate;
    private String model;
    private String color;

    public Car toEntity(CarUpdateDto dto, Car car){
        if(dto.getYear() != null){
            car.setAno(dto.getYear());
        }
        if(dto.getLicensePlate() != null){
          car.setLicensePlate(dto.getLicensePlate());
        }
        if(dto.getModel() != null){
         car.setModel(dto.getModel());
        }
        if(dto.getColor() != null){
         car.setColor(dto.getColor());
        }
        return car;

    }
}
