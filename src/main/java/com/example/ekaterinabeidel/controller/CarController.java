package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.Car;
import com.example.ekaterinabeidel.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByEngineerId")
    public ResponseEntity<List<Car>> getCarsByEngineerId(@RequestParam Long engineerId){
        List<Car> cars = carService.findByEngineerId(engineerId);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByBrandAndModel")
    public ResponseEntity<List<Car>> getCarsByBrandAndModel(
            @RequestParam String brand,@RequestParam String model){
        List<Car> cars = carService.findByBrandAndModel(brand, model);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByYearGreaterThan")
    public ResponseEntity<List<Car>> getCarsByYearGreaterThan(@RequestParam int year){
        List<Car> cars = carService.findByYearGreaterThan(year);
        if(cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
//        Car car = carRepository.findById(id).orElseThrow(
//        ()-> new ResourceNotFoundException("Car not found"));
        Car car = carService.updateCar(id, carDetails);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(carService.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        try {
            carService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

