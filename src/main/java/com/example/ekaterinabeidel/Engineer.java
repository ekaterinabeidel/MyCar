package com.example.ekaterinabeidel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "engineers")
public class Engineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
}
