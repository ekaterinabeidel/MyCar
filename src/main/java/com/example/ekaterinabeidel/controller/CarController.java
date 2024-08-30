package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.dto.CarDTO;
import com.example.ekaterinabeidel.entity.Car;
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
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByEngineerId")
    public ResponseEntity<List<CarDTO>> getCarsByEngineerId(@RequestParam Long engineerId){
        List<CarDTO> cars = carService.findByEngineerId(engineerId);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByBrandAndModel")
    public ResponseEntity<List<CarDTO>> getCarsByBrandAndModel(
            @RequestParam String brand,@RequestParam String model){
        List<CarDTO> cars = carService.findByBrandAndModel(brand, model);
        if (cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/searchByYearGreaterThan") //TODO: обработать случай, когда engineer_id = null
    public ResponseEntity<List<CarDTO>> getCarsByYearGreaterThan(@RequestParam int year){
        List<CarDTO> cars = carService.findByYearGreaterThan(year);
        if(cars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PostMapping //TODO: обсудить создание машины с инженером
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        CarDTO createdCar = carService.save(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    
    @PutMapping("/{id}")//TODO: обсудить обновление машины с инженером
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
//        Car car = carRepository.findById(id).orElseThrow(
//        ()-> new ResourceNotFoundException("Car not found"));
        CarDTO car = carService.updateCar(id, carDTO);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(carService.save(carDTO));
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

