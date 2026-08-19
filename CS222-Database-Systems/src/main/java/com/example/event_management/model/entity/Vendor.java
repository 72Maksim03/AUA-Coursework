package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@ToString
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VendorID")
    private Integer vendorId;

    @Column(name = "Company_name", nullable = false)
    private String CompanyName;

    @Column(name = "Service_type", nullable = false)
    private String ServiceType;

    @Column(name = "Email")
    private String email;
}
