package com.example.event_management.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TotalRevenueDTO {
    private Long eventCount;
    private BigDecimal revenue;
}

