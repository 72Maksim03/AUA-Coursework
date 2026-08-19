package com.example.event_management.repository;

import com.example.event_management.dto.EventInfoDTO;
import com.example.event_management.dto.EventRevenueDTO;
import com.example.event_management.dto.TotalRevenueDTO;
import com.example.event_management.dto.UpcomingEventsDTO;
import com.example.event_management.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT * FROM event WHERE event_type = CAST(:eventType AS EVENT_TYPE_ENUM)", nativeQuery = true)
    List<Event> findByEventType(@Param("eventType") String eventType);

    @Query(value = "SELECT * FROM Event WHERE Start_date BETWEEN :start AND :end", nativeQuery = true)
    List<Event> findEventsBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query(value = "SELECT * FROM Event WHERE event_name ilike %:value%", nativeQuery = true)
    List<Event> findEventByName(@Param("value") String value);

    @Query(value = "SELECT Event.EventID, Event.Event_name, COUNT(Ticket.TicketID) AS Tickets_Sold, SUM(Ticket.price) AS Revenue FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE OrganizerID=:organizerId GROUP BY Event.EventID, Event.Event_name", nativeQuery = true)
    List<EventRevenueDTO> findEventRevenueByOrganizerId(@Param("organizerId") int organizerId);

    @Query(value = "SELECT Event.EventID, Event.Event_name, COUNT(Ticket.TicketID) AS Tickets_Sold, SUM(Ticket.price) AS Revenue FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE Ticket.attendeeID IS NOT NULL GROUP BY Event.EventID, Event.Event_name ORDER BY Revenue DESC", nativeQuery = true)
    List<EventRevenueDTO> ticketSales();

    @Query(value = "SELECT COUNT(DISTINCT Event.EventID) AS Number_Of_Events, SUM(Ticket.Price) FROM Event JOIN Ticket ON Event.EventID=Ticket.EventID WHERE Event.end_date<:endDate AND Event.start_date>:startDate AND Ticket.attendeeId IS NOT NULL", nativeQuery = true)
    List<TotalRevenueDTO> totalRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM get_upcoming_events(:venueId, :initialDate);", nativeQuery = true)
    List<UpcomingEventsDTO> getUpcomingEvents(@Param("venueId") int venueId, @Param("initialDate") LocalDate date);

    @Query(value = "WITH PerformerCount AS (\n" +
            "\tSELECT EventID, COUNT(DISTINCT PerformerID) AS number_of_performers FROM Schedule_performer GROUP BY EventID\n" +
            "),\n" +
            "AttendeeCount AS(\n" +
            "\tSELECT EventID, COUNT(DISTINCT AttendeeID) AS number_of_attendees FROM Ticket GROUP BY EventID\n" +
            ")\n" +
            "SELECT Event.EventID, Event.Event_name, Event.Event_type, Event.Status, Venue.Venue_name, Organizer.name, COALESCE(PerformerCount.number_of_performers, 0) AS performers_number, COALESCE(AttendeeCount.number_of_attendees, 0) AS attendees_number FROM Event JOIN Venue ON Venue.VenueID=Event.VenueID JOIN Organizer ON Organizer.organizerID=Event.OrganizerID LEFT JOIN PerformerCount ON PerformerCount.EventID=Event.EventID LEFT JOIN AttendeeCount ON AttendeeCount.EventID=Event.EventID", nativeQuery = true)
    List<EventInfoDTO> getEventInfo();

    @Query(value = "SELECT profitFromEvent(:eventId)", nativeQuery = true)
    BigDecimal getProfitFromEvent(@Param("eventId") int eventId);
}
