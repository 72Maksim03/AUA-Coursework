package com.example.event_management.repository;

import com.example.event_management.model.entity.Ticket;
import com.example.event_management.model.entity.TicketId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TicketRepository extends JpaRepository<Ticket, TicketId> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Ticket SET AttendeeID = :attendeeId, Purchase_date = :purchaseDate WHERE EventID = :eventId AND Row_number = :rowNumber AND Seat_number = :seatNumber AND AttendeeID IS NULL", nativeQuery = true)
    int bookTicket(@Param("attendeeId") int attendeeId, @Param("purchaseDate") LocalDate pd, @Param("eventId") int eventId, @Param("rowNumber") int rowNumber, @Param("seatNumber") int seatNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Ticket SET AttendeeID = NULL, Purchase_date = NULL, Price = NULL WHERE EventID = :eventId AND Row_number = :rowNumber AND Seat_number = :seatNumber AND AttendeeID = :attendeeId", nativeQuery = true)
    int cancelTicket(@Param("eventId") int eventId, @Param("rowNumber") int rowNumber, @Param("seatNumber") int seatNumber, @Param("attendeeId") int attendeeId);

    @Query(value = "SELECT auto_assign(:eventId, :attendeeId, CAST(:purchaseDate AS DATE), CAST(:price AS NUMERIC(10,2)))", nativeQuery = true)
    String assignTicket(@Param("eventId") int eventId, @Param("attendeeId") int attendeeId, @Param("purchaseDate") LocalDate purchaseDate, @Param("price") Double price);

    @Query(value = "SELECT generateSeats(:eventId, :rowNumber, :seatNumber)", nativeQuery = true)
    void generateSeats(@Param("eventId") int eventId, @Param("rowNumber") int rowNumber, @Param("seatNumber") int seatNumber);


}
