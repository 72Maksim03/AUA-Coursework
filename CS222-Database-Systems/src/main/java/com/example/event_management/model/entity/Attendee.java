package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Attendee")
@Getter
@Setter
@ToString
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttendeeID")
    private int attendeeID;

    @Column(name = "Full_name", nullable = false)
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "Attendee_Preference",
            joinColumns = @JoinColumn(name = "AttendeeID"),
            inverseJoinColumns = @JoinColumn(name = "PreferenceID")
    )
    private List<Preferences> preferences = new ArrayList<>();
}