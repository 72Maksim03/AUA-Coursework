package com.example.event_management.model.entity;

import com.example.event_management.model.enums.ExpertiseArea;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Performer")
@Getter
@Setter
@ToString
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PerformerID")
    private int performerID;

    @Column(name = "Full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Expertise_Area", nullable = false)
    private ExpertiseArea expertiseArea;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PerformerSocialMedia> socialMedia = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Schedule_Performer",
            joinColumns = @JoinColumn(name = "PerformerID"),
            inverseJoinColumns = {
                    @JoinColumn(name = "EventID", referencedColumnName = "EventID"),
                    @JoinColumn(name = "ScheduleID", referencedColumnName = "ScheduleID")
            }
    )
    private List<Schedule> schedules = new ArrayList<>();

}