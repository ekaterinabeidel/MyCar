package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.Car;
import com.example.ekaterinabeidel.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByEngineerName")
    public ResponseEntity<List<Car>> getCarsByEngineerName(@RequestParam Long engineerId){
        List<Car> cars = carRepository.findByEngineerId(engineerId);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByBrandAndModel")
    public ResponseEntity<List<Car>> getCarsByBrandAndModel(
            @RequestParam String brand,@RequestParam String model){
        List<Car> cars = carRepository.findByBrandAndModel(brand, model);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByYearGreaterThan")
    public ResponseEntity<List<Car>> getCarsByYearGreaterThan(@RequestParam int year){
        List<Car> cars = carRepository.findByYearGreaterThan(year);
        if(cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
//        Car car = carRepository.findById(id).orElseThrow(
//        ()-> new ResourceNotFoundException("Car not found"));
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setCityOfManufacture(carDetails.getCityOfManufacture());
        car.setEngineerId(carDetails.getEngineerId());
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        carRepository.delete(car);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

