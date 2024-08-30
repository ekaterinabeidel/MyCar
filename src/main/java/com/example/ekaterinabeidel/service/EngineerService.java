package com.example.ekaterinabeidel.service;

import com.example.ekaterinabeidel.dto.EngineerDTO;
import com.example.ekaterinabeidel.entity.Engineer;
import com.example.ekaterinabeidel.repository.EngineerRepository;
import com.example.ekaterinabeidel.util.EngineerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<EngineerDTO> findAllEngineers() {
        return EngineerMapper.mapToEngineerDTOList(engineerRepository.findAll());
    }

    public EngineerDTO save(EngineerDTO engineerDTO) {

        return EngineerMapper.mapToEngineerDTO(engineerRepository.save(EngineerMapper.mapToEntity(engineerDTO)));
    }

    public EngineerDTO updateEngineers(Long id, EngineerDTO engineerDTO) {
        Optional<Engineer> optionalEngineer = engineerRepository.findById(id);
        if (optionalEngineer.isPresent()) {
            Engineer updatedEngineers = optionalEngineer.get();
            updatedEngineers.setFirstName(engineerDTO.getFirstName());
            updatedEngineers.setLastName(engineerDTO.getLastName());
            updatedEngineers.setDescription(engineerDTO.getDescription());
            return EngineerMapper.mapToEngineerDTO(updatedEngineers);
        }
        return null;
    }

    public void deleteEngineer(Long id) {
        engineerRepository.deleteById(id);
    }
}
