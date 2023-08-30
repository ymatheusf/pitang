package com.desafio.pitang.pitang.controller;

import com.desafio.pitang.pitang.JWT.SecurityFilter;
import com.desafio.pitang.pitang.JWT.TokenService;
import com.desafio.pitang.pitang.model.CarCreateDto;
import com.desafio.pitang.pitang.model.CarUpdateDto;
import com.desafio.pitang.pitang.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> save(HttpServletRequest request,
                                  @RequestBody @Valid CarCreateDto car) {
        try {
            var token = securityFilter.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);
                return carService.save(car, login);
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("invalid session");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error ao Criar Veículo");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        try {
            var token = securityFilter.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);
                return carService.getAllCarsUserPitangId(login);
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("invalid session");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error ao Buscar Veículos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request,
                                     @PathVariable Long id) {
        try {
            var token = securityFilter.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);
                return carService.getById(id, login);
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("invalid session");
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(HttpServletRequest request,
                                        @PathVariable Long id) {
        try {
            var token = securityFilter.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(carService.deleteById(id, login));
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("invalid session");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(HttpServletRequest request,
                                        @PathVariable Long id,
                                        @RequestBody CarUpdateDto car) {
        try {
            var token = securityFilter.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(carService.updateById(id, login, car));
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("invalid session");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ERROR");
        }
    }

}
