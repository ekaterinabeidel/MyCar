package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.dto.EngineerCreateDTO;
import com.example.ekaterinabeidel.dto.EngineerResponseDTO;
import com.example.ekaterinabeidel.dto.EngineerUpdateDTO;
import com.example.ekaterinabeidel.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engineers")
public class EngineerController {

    @Autowired
    private EngineerService engineerService;

    @GetMapping
    public ResponseEntity<List<EngineerResponseDTO>> getAllEngineers() {
        List<EngineerResponseDTO> engineers = engineerService.findAllEngineers();
        return ResponseEntity.status(HttpStatus.OK).body(engineers);
    }

    @PostMapping
    public ResponseEntity<EngineerResponseDTO> createEngineer(@RequestBody EngineerCreateDTO engineerCreateDTO) {
        EngineerResponseDTO createdEngineerDTO = engineerService.save(engineerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEngineerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EngineerResponseDTO> updateEngineer(@PathVariable Long id, @RequestBody EngineerUpdateDTO engineerUpdateDTO) {
        EngineerResponseDTO updatedEngineerDTO = engineerService.updateEngineers(id, engineerUpdateDTO);
        if (updatedEngineerDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedEngineerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id) {
        engineerService.deleteEngineer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
