package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ActiveAttendeeDTO {
    private int attendeeId;
    private String fullName;
    private String email;
    private Long numOfEvents;
}