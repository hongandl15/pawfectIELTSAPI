package com.pawfectielts.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "testDetail_id")
    private TestDetail testDetail;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "part_order")
    private int order;

    @Column(name = "content", length = 25000)
    private String content;

    // Getters and setters
}