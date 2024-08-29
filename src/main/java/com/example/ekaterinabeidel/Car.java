package com.example.ekaterinabeidel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer;
}
/**
 * Аннотация @Entity
 * Обозначение сущности: Аннотация @Entity указывает, что данный класс является сущностью JPA.
 * Это означает, что экземпляры этого класса могут быть сохранены в базе данных и извлечены из нее.
 * Обязательное требование:
 * Каждый класс, который должен быть связан с таблицей в базе данных, должен быть аннотирован @Entity.
 * Без этой аннотации JPA не будет рассматривать класс как сущность.
 * <p>
 * Аннотация @Table
 * Настройка имени таблицы: Аннотация @Table используется для указания имени таблицы в базе данных,
 * с которой связана сущность. Если эта аннотация не указана,
 * JPA по умолчанию использует имя класса для определения имени таблицы.
 * Дополнительные параметры: Аннотация @Table также позволяет задавать другие параметры,
 * такие как уникальные ограничения и индексы, которые могут быть применены к таблице.
 */

