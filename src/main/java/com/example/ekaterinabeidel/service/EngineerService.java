package com.example.ekaterinabeidel.service;

import com.example.ekaterinabeidel.dto.EngineerCreateDTO;
import com.example.ekaterinabeidel.dto.EngineerResponseDTO;
import com.example.ekaterinabeidel.dto.EngineerUpdateDTO;
import com.example.ekaterinabeidel.entity.Engineer;
import com.example.ekaterinabeidel.exception.CarNotFoundException;
import com.example.ekaterinabeidel.exception.EngineerNotFoundException;
import com.example.ekaterinabeidel.repository.EngineerRepository;
import com.example.ekaterinabeidel.util.EngineerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<EngineerResponseDTO> findAllEngineers() {
        return EngineerMapper.mapToEngineerDTOList(engineerRepository.findAll());
    }

    public EngineerResponseDTO save(EngineerCreateDTO engineerCreateDTO) {
        Engineer engineer = EngineerMapper.mapEngineerCreateDTOToEntity(engineerCreateDTO);
        Engineer createdEngineer = engineerRepository.save(engineer);
        return EngineerMapper.mapToEngineerResponseDTO(createdEngineer);
    }

    public EngineerResponseDTO updateEngineers(Long id, EngineerUpdateDTO engineerUpdateDTO) {
        Engineer updatedEngineer = engineerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Engineer with ID " + id + " not found")
        );
        updatedEngineer.setFirstName(engineerUpdateDTO.getFirstName());
        updatedEngineer.setLastName(engineerUpdateDTO.getLastName());
        updatedEngineer.setDescription(engineerUpdateDTO.getDescription());
        return EngineerMapper.mapToEngineerResponseDTO(updatedEngineer);
    }

    public void deleteEngineer(Long id) {
        if (!engineerRepository.existsById(id)){
            throw new EngineerNotFoundException("Engineer with ID " + id + " not found");
        } else {
            engineerRepository.deleteById(id);
        }
    }
}
