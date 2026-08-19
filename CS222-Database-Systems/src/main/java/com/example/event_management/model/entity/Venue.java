package com.example.event_management.model.entity;

import com.example.event_management.model.enums.VenueType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Venue")
@Getter
@Setter
@ToString
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VenueID")
    private int venueID;

    @Column(name = "Venue_name", nullable = false)
    private String venueName;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Street", nullable = false)
    private String street;

    @Column(name = "ZIP", nullable = false)
    private String zip;

    @Column(name = "Capacity", nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "Venue_type", nullable = false)
    private VenueType venueType;
}