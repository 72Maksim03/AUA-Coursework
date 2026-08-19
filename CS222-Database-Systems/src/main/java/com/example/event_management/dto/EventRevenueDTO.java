package com.example.event_management.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class EventRevenueDTO {
    private int eventId;
    private String eventName;
    private Long ticketsSold;
    private BigDecimal revenue;
}