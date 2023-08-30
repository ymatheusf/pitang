package com.desafio.pitang.pitang.service;

import com.desafio.pitang.pitang.entity.Car;
import com.desafio.pitang.pitang.entity.UserPitang;
import com.desafio.pitang.pitang.model.CarCreateDto;
import com.desafio.pitang.pitang.model.CarUpdateDto;
import com.desafio.pitang.pitang.model.UserReturnDto;
import com.desafio.pitang.pitang.repository.CarRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class CarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    UserService userService;

    public UserReturnDto toDto(UserPitang user) {
        return new UserReturnDto(
                user.getFistName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin(),
                user.getPhone(),
                this.getCars(user.getId()),
                user.getCreatedAt(),
                user.getLastLogin()
        );
    }

    public Car toEntity(CarCreateDto carDto, UserPitang userPitangId) {
        Car car = new Car();
        car.setAno(carDto.getYear());
        car.setColor(carDto.getColor());
        car.setModel(carDto.getModel());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setUserPitangId(userPitangId);
        return car;
    }


    public List<Car> getCars(Long id) {
        return carRepository.getByUserPitangId(id);
    }

    public ResponseEntity<?> getAllCarsUserPitangId(String login) {
        UserPitang user = userService.getByLogin(login);
        List<Car> list = this.getCars(user.getId());
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public Car findByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    public ResponseEntity<?> save(CarCreateDto request, String login) throws Exception {
        if (this.findByLicensePlate(request.getLicensePlate()) != null)
            throw new Exception("License Plate already exists");
        UserPitang user = userService.getByLogin(login);
        return new ResponseEntity<>(carRepository.save(this.toEntity(request, user)), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getById(Long id, String login) {
        UserPitang user = userService.getByLogin(login);
        Car car = carRepository.findByIdAndUserPitangId(id, user.getId());
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    public String deleteById(Long id, String login) {
        UserPitang user = userService.getByLogin(login);
        Car car = carRepository.findByIdAndUserPitangId(id, user.getId());
        if (car != null) {
            carRepository.deleteById(car.getId());
            return "Carro deletado com sucesso";
        }
        return "Este Veículo não pertence ao Usuário Logado";
    }

    public String updateById(Long id, String login, CarUpdateDto dto) {
        UserPitang user = userService.getByLogin(login);
        Car car = carRepository.findByIdAndUserPitangId(id, user.getId());
        if (car != null) {
            carRepository.save(new CarUpdateDto().toEntity(dto, car));
            return "Carro Atualizado com sucesso";
        }
        return "Este Veículo não pertence ao Usuário Logado";

    }

}
