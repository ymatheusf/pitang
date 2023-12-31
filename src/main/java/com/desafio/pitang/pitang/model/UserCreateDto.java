package com.desafio.pitang.pitang.model;

import com.desafio.pitang.pitang.entity.Car;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.service.CarService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class UserCreateDto{
    @NotBlank(message = "not.blank")
    private String fistName;
    @NotBlank(message = "not.blank")
    private String lastName;
    @NotBlank(message = "not.blank")
    @Email
    private String email;
    private Date birthday;
    @NotBlank(message = "not.blank")
    private String login;
    @NotBlank(message = "not.blank")
    private String password;
    @NotBlank(message = "not.blank")
    private String phone;

    private List<CarCreateDto> cars;

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CarCreateDto> getCars() {
        return cars;
    }

    public void setCars(List<CarCreateDto> cars) {
        this.cars = cars;
    }

    public List<Car> toEntityCars(List<CarCreateDto> carsDto, UserPitang userPitang){
        List<Car> listCar = listOf();
        Car car = new Car();
       for( CarCreateDto carDto : carsDto){
           car.setColor(carDto.getColor());
           car.setModel(carDto.getModel());
           car.setAno(carDto.getYear());
           car.setLicensePlate(carDto.getLicensePlate());
           car.setUserPitangId(userPitang);
           listCar.add(car);
        }
       return listCar;
    }

}
