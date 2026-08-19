package com.example.event_management.model.entity;

import com.example.event_management.model.enums.EventStatus;
import com.example.event_management.model.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Event")
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventID")
    private int eventID;

    @Column(name = "Event_name", nullable = false)
    private String eventName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Event_type", columnDefinition = "EVENT_TYPE_ENUM", nullable = false)
    private EventType eventType;

    @Column(name = "Start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "End_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Total_Budget", nullable = false)
    private BigDecimal totalBudget;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "VenueID")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "AdminID")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "OrganizerID")
    private Organizer organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
}