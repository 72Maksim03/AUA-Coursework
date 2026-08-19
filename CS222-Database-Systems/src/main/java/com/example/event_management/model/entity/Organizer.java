package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organizer")
@Getter
@Setter
@ToString
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrganizerID")
    private Integer organizerId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Address", nullable = false)
    private String address;
}
