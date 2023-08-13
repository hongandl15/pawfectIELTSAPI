package com.pawfectielts.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questionDetail")
@Getter
@Setter
public class QuestionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionDetail_id")
    private Long id;

    private int order;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String name;

    // Getters and setters
}