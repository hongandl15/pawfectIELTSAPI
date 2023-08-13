package com.pawfectielts.entity;
import  jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "testResult")
@Data
@Getter
@Setter
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testResult_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int score;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    // Getters and setters
}
