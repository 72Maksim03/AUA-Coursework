package com.example.event_management.dto;

import lombok.*;
import org.postgresql.util.PGInterval;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PerformanceDTO {
    private int performerId;
    private String fullName;
    private Long numberOfEvents;
    private Long averageActivities;
    private PGInterval avgDuration;
}
