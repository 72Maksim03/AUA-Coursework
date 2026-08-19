package com.example.event_management.model.entity;

import com.example.event_management.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Event_Vendor")
@Getter
@Setter
@ToString
public class EventVendor {
    @EmbeddedId
    private EventVendorId id;

    @ManyToOne
    @MapsId("eventID")
    @JoinColumn(name = "EventID")
    private Event event;

    @ManyToOne
    @MapsId("vendorID")
    @JoinColumn(name = "VendorID")
    private Vendor vendor;

    @Column(name = "Contract_amount")
    private BigDecimal contractAmount;

    @Column(name = "Service_date", nullable = false)
    private LocalDate serviceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Payment_status", nullable = false)
    private PaymentStatus paymentStatus;
}