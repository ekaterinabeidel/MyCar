package com.example.ekaterinabeidel.controller;

import com.example.ekaterinabeidel.dto.EngineerDTO;
import com.example.ekaterinabeidel.entity.Engineer;
import com.example.ekaterinabeidel.service.EngineerService;
import com.example.ekaterinabeidel.util.EngineerMapper;
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
    public ResponseEntity<List<EngineerDTO>> getAllEngineers(){
        List<EngineerDTO> engineers = engineerService.findAllEngineers();
        return ResponseEntity.status(HttpStatus.OK).body(engineers);
    }

    @PostMapping
    public ResponseEntity<EngineerDTO> createEngineer(@RequestBody EngineerDTO engineerDTO){
        EngineerDTO createdEngineerDTO = engineerService.save(engineerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEngineerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EngineerDTO> updateEngineer(@PathVariable Long id, @RequestBody EngineerDTO engineerDTO){
        EngineerDTO updatedEngineerDTO = engineerService.updateEngineers(id, engineerDTO);
        if (updatedEngineerDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedEngineerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id){
        try{
            engineerService.deleteEngineer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
