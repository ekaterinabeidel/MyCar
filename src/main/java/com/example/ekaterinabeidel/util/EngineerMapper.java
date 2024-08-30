package com.example.ekaterinabeidel.util;

import com.example.ekaterinabeidel.dto.CarDTO;
import com.example.ekaterinabeidel.dto.EngineerDTO;
import com.example.ekaterinabeidel.entity.Car;
import com.example.ekaterinabeidel.entity.Engineer;

import java.util.List;
import java.util.stream.Collectors;

public class EngineerMapper {

    public static EngineerDTO mapToEngineerDTO(Engineer engineer) {
        EngineerDTO engineerDTO = new EngineerDTO();
        engineerDTO.setFirstName(engineer.getFirstName());
        engineerDTO.setLastName(engineer.getLastName());
        engineerDTO.setDescription(engineer.getDescription());
        return engineerDTO;
    }

    public static List<EngineerDTO> mapToEngineerDTOList(List<Engineer> engineers) {
        return engineers.stream().map(EngineerMapper::mapToEngineerDTO).collect(Collectors.toList());
    }

    public static Engineer mapToEntity(EngineerDTO engineerDTO) {
        Engineer engineer = new Engineer();
        engineer.setFirstName(engineerDTO.getFirstName());
        engineer.setLastName(engineerDTO.getLastName());
        engineer.setDescription(engineerDTO.getDescription());
        return engineer;
    }
}
