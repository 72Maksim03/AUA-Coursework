package com.example.event_management.dto;

import com.example.event_management.model.enums.EventStatus;
import com.example.event_management.model.enums.EventType;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class EventInfoDTO {
    private int eventID;
    private String eventName;
    private String eventType;
    private String status;
    private String venueName;
    private String organizerName;
    private Long performersNumber;
    private Long attendeesNumber;
}