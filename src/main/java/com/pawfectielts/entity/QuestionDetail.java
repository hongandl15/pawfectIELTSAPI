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

    @Column(name = "questionDetail_order")
    private int order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "name", length = 25000)
    private String name;

    // Getters and setters
}