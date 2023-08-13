package com.pawfectielts.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "questionType_id")
    private QuestionType questionType;

    @Column(name = "question_order")
    private int order;

    @Column(name = "title", length = 25000)
    private String title;

    // Getters and setters
}