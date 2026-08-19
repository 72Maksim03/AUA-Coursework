package com.example.event_management.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SupplierNumDTO {
    private int supplierId;
    private String supplierName;
    private Long numOfProv;
}
