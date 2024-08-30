package com.example.ekaterinabeidel.repository;

import com.example.ekaterinabeidel.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
