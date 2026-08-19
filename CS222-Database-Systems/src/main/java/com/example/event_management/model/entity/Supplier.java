package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@ToString
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private Integer supplierID;

    @Column(name = "Supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "Product_type", nullable = false)
    private String productType;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;
}