package com.example.ekaterinabeidel.service;

import com.example.ekaterinabeidel.dto.CarCreateDTO;
import com.example.ekaterinabeidel.dto.CarResponseDTO;
import com.example.ekaterinabeidel.dto.CarUpdateDTO;
import com.example.ekaterinabeidel.entity.Car;
import com.example.ekaterinabeidel.entity.Engineer;
import com.example.ekaterinabeidel.exception.CarNotFoundException;
import com.example.ekaterinabeidel.exception.EngineerNotFoundException;
import com.example.ekaterinabeidel.repository.CarRepository;
import com.example.ekaterinabeidel.repository.EngineerRepository;
import com.example.ekaterinabeidel.util.CarMapper;
import com.example.ekaterinabeidel.util.EngineerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    public List<CarResponseDTO> getAllCars() {
        return CarMapper.mapToListCarResponseDTO(carRepository.findAll());
    }

    public List<CarResponseDTO> findByEngineerId(Long engineerId) {
        return CarMapper.mapToListCarResponseDTO(carRepository.findByEngineerId(engineerId));
    }

    public List<CarResponseDTO> findByBrandAndModel(String brand, String model) {
        return CarMapper.mapToListCarResponseDTO(carRepository.findByBrandAndModel(brand, model));
    }

    public List<CarResponseDTO> findByYearGreaterThan(int year) {
        return CarMapper.mapToListCarResponseDTO(carRepository.findByYearGreaterThan(year));
    }

    public CarResponseDTO save(CarCreateDTO carCreateDTO) {
        Car car = CarMapper.mapCarCreateDTOToEntity(carCreateDTO);
        if (carCreateDTO.getEngineerId() != null) {
            Engineer engineer = engineerRepository.findById(carCreateDTO.getEngineerId())
                    .orElseThrow(() -> new EntityNotFoundException("Engineer with ID " + carCreateDTO.getEngineerId() + " not found"));
            car.setEngineer(engineer);
        }
        Car savedCar = carRepository.save(car);
        return CarMapper.mapToCarResponseDTO(savedCar);
    }

    public CarResponseDTO updateCar(Long id, CarUpdateDTO carUpdateDTO) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new CarNotFoundException("Car with ID " + id + " not found")
        );
        Engineer engineer = engineerRepository.findById(carUpdateDTO.getEngineerId()).orElseThrow(
                () -> new EngineerNotFoundException("Engineer with ID " + carUpdateDTO.getEngineerId() + " not found")
        );
        car.setModel(carUpdateDTO.getModel());
        car.setBrand(carUpdateDTO.getBrand());
        car.setYear(carUpdateDTO.getYear());
        car.setCityOfManufacture(carUpdateDTO.getCityOfManufacture());
        car.setEngineer(engineer);
        Car updatedCar = carRepository.save(car);
        return CarMapper.mapToCarResponseDTO(updatedCar);
    }

    public void delete(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car with ID " + id + " not found");
        } else {
            carRepository.deleteById(id);
        }
    }
}
