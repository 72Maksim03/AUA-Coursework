package com.example.event_management.model.entity;

import com.example.event_management.model.enums.EquipmentCondition;
import com.example.event_management.model.enums.EquipmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Venue_Equipment")
@Getter
@Setter
@ToString
public class VenueEquipment {

    @EmbeddedId
    private VenueEquipmentId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Equipment_type", nullable = false)
    private EquipmentType equipmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Condition", nullable = false)
    private EquipmentCondition condition;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @MapsId("venueId")
    @JoinColumn(name = "VenueID")
    private Venue venue;
}
