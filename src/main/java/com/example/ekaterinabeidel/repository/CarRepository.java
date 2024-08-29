package com.example.ekaterinabeidel.repository;

import com.example.ekaterinabeidel.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByEngineerId(Long engineerId);
    List<Car> findByBrandAndModel(String brand, String model);
    List<Car> findByYearGreaterThan(int year);

}
