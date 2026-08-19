package com.example.event_management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Schedule")
@Getter
@Setter
@ToString
public class Schedule {
    @EmbeddedId
    private ScheduleId id;

    @ManyToOne
    @MapsId("eventID")
    @JoinColumn(name = "EventID")
    @JsonIgnore
    private Event event;

    @Column(name = "Event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "Start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "End_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "Activity_name", nullable = false)
    private String activityName;
}