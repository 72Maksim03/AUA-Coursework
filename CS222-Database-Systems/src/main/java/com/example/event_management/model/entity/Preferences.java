package com.example.event_management.model.entity;

import com.example.event_management.model.enums.PreferenceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Preferences")
@Getter
@Setter
@ToString
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PreferenceID")
    private int preferenceID;

    @Enumerated(EnumType.STRING)
    @Column(name = "Preference_type", nullable = false)
    private PreferenceType preferenceType;

    @Column(name = "Preference_value", nullable = false)
    private String preferenceValue;

    @ManyToMany(mappedBy = "preferences")
    @JsonIgnore
    private List<Attendee> attendees = new ArrayList<>();
}