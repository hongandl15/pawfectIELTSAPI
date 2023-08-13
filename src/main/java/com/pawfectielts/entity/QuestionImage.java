package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questionImage")
@Getter
@Setter
public class QuestionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "questionImage_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String image;

    // Getters and setters
}
