package com.pawfectielts.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "passageResult")
public class PassageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passageResult_id")
    private Long id;

    @Column(name = "passageResult_order")
    private int order;
    private double scoreTaskResponse;
    private double scoreCoherence;
    private double scoreLexical;
    private double scoreGrammar;
    private double overallScore;
    @Column(name = "content", length = 25000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "testResult_id")
    private TestResult testResult;

}
