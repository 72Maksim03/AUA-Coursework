package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TicketSalesDTO {
    private int eventId;
    private String eventName;
    private Long ticketsSold;
    private Long revenue;
}
