package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class VenueUsedDTO {
    private int venueId;
    private String venueName;
    private Long usedCount;
}