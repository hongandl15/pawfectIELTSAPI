package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "answer_order")
    private int order;

    private String correctAnswer;

    // Getters and setters
}
