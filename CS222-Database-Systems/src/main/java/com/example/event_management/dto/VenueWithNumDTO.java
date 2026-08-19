package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class VenueWithNumDTO {
    private int venueId;
    private String venueName;
    private Long numberOfEvents;
}
