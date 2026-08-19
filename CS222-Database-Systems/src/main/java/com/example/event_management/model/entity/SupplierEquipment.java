package com.example.event_management.model.entity;

import com.example.event_management.model.enums.EquipmentAvailabilityStatus;
import com.example.event_management.model.enums.EquipmentCondition;
import com.example.event_management.model.enums.EquipmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Supplier_Equipment")
@Getter
@Setter
@ToString
public class SupplierEquipment {

    @EmbeddedId
    private SupplierEquipmentId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Equipment_type", nullable = false)
    private EquipmentType equipmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Condition", nullable = false)
    private EquipmentCondition condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "Availability_status", nullable = false)
    private EquipmentAvailabilityStatus availabilityStatus;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;
}
