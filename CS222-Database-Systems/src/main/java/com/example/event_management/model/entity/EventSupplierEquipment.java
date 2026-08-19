package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "Event_Supplier_equipment")
@Getter
@Setter
@ToString
public class EventSupplierEquipment {

    @EmbeddedId
    private EventSupplierEquipmentId id;

    @Column(name = "Price")
    private BigDecimal price;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "EventID")
    private Event event;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID", insertable = false, updatable = false),
            @JoinColumn(name = "EquipmentID", referencedColumnName = "EquipmentID", insertable = false, updatable = false)
    })
    private SupplierEquipment supplierEquipment;
}