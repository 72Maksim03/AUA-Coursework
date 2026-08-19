package com.example.event_management.repository;

import com.example.event_management.dto.VenueStatusDTO;
import com.example.event_management.dto.VenueUsedDTO;
import com.example.event_management.dto.VenueWithNumDTO;
import com.example.event_management.model.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
    @Query(value = "SELECT * FROM Venue WHERE Venue_type = CAST(:venueType AS VENUE_TYPE_ENUM)", nativeQuery = true)
    List<Venue> getVenuesFromType(@Param("venueType") String venueType);

    @Query(value = "SELECT * FROM Venue WHERE VenueID NOT IN (\n" +
            "\tSELECT VenueID FROM Event WHERE start_date < :endDateTime AND End_date > :startDateTime\n" +
            ")", nativeQuery = true)
    List<Venue> getAvailableVenues(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query(value = "SELECT Venue.VenueID, Venue.Venue_name, \n" +
            "CASE\n" +
            "\tWHEN Event.EventID IS NULL THEN 'Available'\n" +
            "\tELSE 'Booked'\n" +
            "END AS Availability\n" +
            "FROM Venue LEFT JOIN Event ON Event.VenueID=Venue.VenueID AND start_date<:endDate AND end_date>:startDate\n" +
            "ORDER BY Venue.VenueID;", nativeQuery = true)
    List<VenueStatusDTO> getVenuesWithStatus(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT Venue.VenueID, Venue.Venue_name, COUNT(Event.EventID) AS number_of_times_used FROM Venue JOIN EVENT ON Venue.venueID=Event.venueID WHERE Event.end_date<:endDate AND Event.start_date>:startDate GROUP BY Venue.VenueID, Venue.Venue_name", nativeQuery = true)
    List<VenueUsedDTO> getNumberOfTimesVenuesUsed(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT Venue.VenueID, Venue.Venue_name, COUNT(DISTINCT Event.EventID) AS number_of_events FROM Venue JOIN Event ON Venue.VenueID=Event.VenueID WHERE Event.Start_date>:date GROUP BY Venue.VenueID, Venue.Venue_name ORDER BY number_of_events DESC", nativeQuery = true)
    List<VenueWithNumDTO> getNumberOfUpcoming(@Param("date")LocalDate date);
}