package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_answer_id")
    private Long id;

    @Column(name = "order_number", columnDefinition = "INTEGER")
    private int orderNumber;
    private boolean correct;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "testResult_id")
    private TestResult testResult;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    // Getters and setters
}
