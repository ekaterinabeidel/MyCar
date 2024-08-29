package com.example.ekaterinabeidel;

import com.example.ekaterinabeidel.Car;
import com.example.ekaterinabeidel.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public List<Car> findByEngineerId(Long engineerId) {
        return carRepository.findByEngineerId(engineerId);
    }

    public List<Car> findByBrandAndModel(String brand, String model) {
        return carRepository.findByBrandAndModel(brand, model);
    }

    public List<Car> findByYearGreaterThan(int year) {
        return carRepository.findByYearGreaterThan(year);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            Car car = optionalCar.get();
            car.setModel(carDetails.getModel());
            car.setBrand(carDetails.getBrand());
            car.setYear(carDetails.getYear());
            car.setCityOfManufacture(carDetails.getCityOfManufacture());
            car.setEngineerId(carDetails.getEngineerId());
            return carRepository.save(car);
        }
        return null;
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
