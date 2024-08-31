package com.example.ekaterinabeidel.util;

import com.example.ekaterinabeidel.dto.EngineerCreateDTO;
import com.example.ekaterinabeidel.dto.EngineerResponseDTO;
import com.example.ekaterinabeidel.dto.EngineerUpdateDTO;
import com.example.ekaterinabeidel.entity.Engineer;

import java.util.List;
import java.util.stream.Collectors;

public class EngineerMapper {

    public static EngineerResponseDTO mapToEngineerResponseDTO(Engineer engineer) {
        EngineerResponseDTO engineerResponseDTO = new EngineerResponseDTO();
        engineerResponseDTO.setId(engineer.getId());
        engineerResponseDTO.setFirstName(engineer.getFirstName());
        engineerResponseDTO.setLastName(engineer.getLastName());
        engineerResponseDTO.setDescription(engineer.getDescription());
        return engineerResponseDTO;
    }

    public static List<EngineerResponseDTO> mapToEngineerDTOList(List<Engineer> engineers) {
        return engineers.stream().map(EngineerMapper::mapToEngineerResponseDTO).collect(Collectors.toList());
    }

    public static Engineer mapEngineerCreateDTOToEntity(EngineerCreateDTO engineerCreateDTO) {
        Engineer engineer = new Engineer();
        engineer.setFirstName(engineerCreateDTO.getFirstName());
        engineer.setLastName(engineerCreateDTO.getLastName());
        engineer.setDescription(engineerCreateDTO.getDescription());
        return engineer;
    }
}
