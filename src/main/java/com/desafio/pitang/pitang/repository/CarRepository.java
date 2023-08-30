package com.desafio.pitang.pitang.repository;

import com.desafio.pitang.pitang.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM TABLE_CAR TB WHERE TB.USER_PITANG_ID = :id", nativeQuery = true)
    List<Car> getByUserPitangId(Long id);

    Car findByLicensePlate(String licensePlate);

    @Query(value = "SELECT * FROM TABLE_CAR TB WHERE TB.ID = :id AND TB.USER_PITANG_ID = :userPitangId", nativeQuery = true)
    Car findByIdAndUserPitangId(Long id, Long userPitangId);
}
