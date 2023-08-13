package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questionType")
@Getter
@Setter
@Data
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionType_id")
    private Long id;

    private String name;

    // Getters and setters
}