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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;


    @Column(name = "part_order")
    private int order;

    @Column(name = "content", length = 2500000)
    private String content;

    @Column(name = "audio_file", length = 25000)
    private String audioFile;

    // Getters and setters
}