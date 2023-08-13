package com.pawfectielts.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questionDetailChild")
@Getter
@Setter
public class QuestionDetailChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionDetailChild_id")
    private Long id;


    private String content;

    @ManyToOne
    @JoinColumn(name = "questionDetail_id")
    private QuestionDetail questionDetail;

    // Getters and setters
}