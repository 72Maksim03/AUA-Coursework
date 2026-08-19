package com.example.event_management.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class VendorInfoDTO {
    private int vendorId;
    private String companyName;
    private String serviceType;
    private Long numOfEvents;
    private BigDecimal avgAmount;
}