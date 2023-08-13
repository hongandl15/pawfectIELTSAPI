package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recording")
@Getter
@Setter
public class Recording {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recording_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;
    private String audio;

    // Getters and setters
}
