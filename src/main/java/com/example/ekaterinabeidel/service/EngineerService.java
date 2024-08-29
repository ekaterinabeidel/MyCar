package com.example.ekaterinabeidel.service;

import com.example.ekaterinabeidel.Engineer;
import com.example.ekaterinabeidel.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<Engineer> findAllEngineers() {
        return engineerRepository.findAll();
    }

    public Engineer save(Engineer engineer) {
        return engineerRepository.save(engineer);
    }

    public Engineer updateEngineers(Long id, Engineer engineer) {
        Optional<Engineer> optionalEngineer = engineerRepository.findById(id);
        if (optionalEngineer.isPresent()) {
            Engineer updatedEngineers = optionalEngineer.get();
            updatedEngineers.setFirstName(engineer.getFirstName());
            updatedEngineers.setLastName(engineer.getLastName());
            updatedEngineers.setDescription(engineer.getDescription());
            return updatedEngineers;
        }
        return null;
    }

    public void deleteEngineer(Long id) {
        engineerRepository.deleteById(id);
    }
}
