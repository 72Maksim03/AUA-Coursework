package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admin")
@Getter
@Setter
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminID")
    private int adminID;

    @Column(name = "Full_name", nullable = false)
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;
}