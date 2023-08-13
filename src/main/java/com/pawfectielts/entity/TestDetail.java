package com.pawfectielts.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "testDetail")
@Getter
@Setter
public class TestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testDetail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    // Getters and setters
}