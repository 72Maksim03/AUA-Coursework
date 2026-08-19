package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class VenueStatusDTO {
    private int venueId;
    private String venueName;
    private String status;
}
