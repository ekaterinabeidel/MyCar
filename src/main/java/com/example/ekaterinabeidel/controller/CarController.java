package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.dto.CarCreateDTO;
import com.example.ekaterinabeidel.dto.CarResponseDTO;
import com.example.ekaterinabeidel.dto.CarUpdateDTO;
import com.example.ekaterinabeidel.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Получить все автомобили",
            description = "Возвращает список всех автомобилей с подробной информацией")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка автомобилей")
    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> cars = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @Operation(
            summary = "Получить автомобили по индификотору инженера",
            description = "Возвращает список атомобилей с подробной информацией об атомобиле и об инженере")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка автомобилей"),
            @ApiResponse(responseCode = "404", description = "Автомобили с указанным идентификатором инженера не найдены")
    })
    @GetMapping("/searchByEngineerId")
    public ResponseEntity<List<CarResponseDTO>> getCarsByEngineerId(@RequestParam Long engineerId) {
        List<CarResponseDTO> cars = carService.findByEngineerId(engineerId);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @Operation(
            summary = "Получить автомобили по бренду и модели",
            description = "Возвращает список автомобилей по заданным параметрам бренда и модели")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка автомобилей"),
            @ApiResponse(responseCode = "404", description = "Автомобили с указанными брендом и моделью не найдены")
    })
    @GetMapping("/searchByBrandAndModel")
    public ResponseEntity<List<CarResponseDTO>> getCarsByBrandAndModel(
            @RequestParam String brand, @RequestParam String model) {
        List<CarResponseDTO> cars = carService.findByBrandAndModel(brand, model);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @Operation(
            summary = "Получить автомобили по году выпуска",
            description = "Возвращает список автомобилей, у которых год выпуска больше указанного")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка автомобилей"),
            @ApiResponse(responseCode = "404", description = "Автомобили с годом выпуска больше указанного не найдены")
    })
    @GetMapping("/searchByYearGreaterThan")
    public ResponseEntity<List<CarResponseDTO>> getCarsByYearGreaterThan(@RequestParam int year) {
        List<CarResponseDTO> cars = carService.findByYearGreaterThan(year);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @Operation(
            summary = "Создать новый автомобиль",
            description = "Создает новый автомобиль и возвращает его подробную информацию")
    @ApiResponse(responseCode = "201", description = "Автомобиль успешно создан")
    @PostMapping
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody CarCreateDTO carCreateDTO) {
        CarResponseDTO createdCar = carService.save(carCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @Operation(
            summary = "Обновить информацию об автомобиле",
            description = "Обновляет информацию об автомобиле по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Информация об автомобиле успешно обновлена")
    @ApiResponse(responseCode = "404", description = "Автомобиль с указанным идентификатором не найден")
    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody CarUpdateDTO carUpdateDTO) {
        CarResponseDTO updatedCar = carService.updateCar(id, carUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @Operation(
            summary = "Удалить автомобиль",
            description = "Удаляет автомобиль по его идентификатору")
    @ApiResponse(responseCode = "204", description = "Автомобиль успешно удален")
    @ApiResponse(responseCode = "404", description = "Автомобиль с указанным идентификатором не найден")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

