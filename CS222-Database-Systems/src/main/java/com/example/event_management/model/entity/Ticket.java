package com.example.event_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@ToString
public class Ticket {

    @EmbeddedId
    private TicketId id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "EventID")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "AttendeeID")
    private Attendee attendee;

    @Column(name = "Purchase_Date")
    private LocalDate purchaseDate;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Row_number", nullable = false)
    private Integer rowNumber;

    @Column(name = "Seat_number", nullable = false)
    private Integer seatNumber;
}
