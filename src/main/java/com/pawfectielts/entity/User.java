package com.pawfectielts.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String role;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    // Getters and setters
}