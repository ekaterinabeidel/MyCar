package com.example.ekaterinabeidel.service;

import com.example.ekaterinabeidel.dto.CarDTO;
import com.example.ekaterinabeidel.entity.Car;
import com.example.ekaterinabeidel.repository.CarRepository;
import com.example.ekaterinabeidel.util.CarMapper;
import com.example.ekaterinabeidel.util.EngineerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        return CarMapper.mapToListDTO(carRepository.findAll());
    }

    public List<CarDTO> findByEngineerId(Long engineerId) {
        return CarMapper.mapToListDTO(carRepository.findByEngineerId(engineerId));
    }

    public List<CarDTO> findByBrandAndModel(String brand, String model) {
        return CarMapper.mapToListDTO(carRepository.findByBrandAndModel(brand, model));
    }

    public List<CarDTO> findByYearGreaterThan(int year) {
        return CarMapper.mapToListDTO(carRepository.findByYearGreaterThan(year));
    }

    public CarDTO save(CarDTO carDTO) {
        return CarMapper.mapToDTO(carRepository.save(CarMapper.mapToEntity(carDTO)));
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setModel(carDTO.getModel());
            car.setBrand(carDTO.getBrand());
            car.setYear(carDTO.getYear());
            car.setCityOfManufacture(carDTO.getCityOfManufacture());
            car.setEngineer(EngineerMapper.mapToEntity(carDTO.getEngineerDTO()));
            return CarMapper.mapToDTO(carRepository.save(car));
        }
        return null;
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
