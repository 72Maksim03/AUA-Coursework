package com.example.event_management.repository;

import com.example.event_management.dto.ActiveAttendeeDTO;
import com.example.event_management.model.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
    @Query(value = "SELECT Attendee.AttendeeID, Attendee.Full_name, Attendee.email, COUNT(DISTINCT Event.EventID) AS number_of_events FROM Attendee JOIN Ticket ON Attendee.AttendeeID=Ticket.AttendeeID JOIN Event ON Ticket.EventID=Event.EventID WHERE Event.end_date<:endDate AND Event.start_date>:startDate GROUP BY Attendee.AttendeeID, Attendee.Full_name, Attendee.email ORDER BY number_of_events DESC LIMIT 10", nativeQuery = true)
    List<ActiveAttendeeDTO> getActiveAttendees(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
