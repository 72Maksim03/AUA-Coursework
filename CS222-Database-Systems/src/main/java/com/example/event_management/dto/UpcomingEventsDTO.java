package com.example.event_management.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UpcomingEventsDTO {
    private int eventId;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}