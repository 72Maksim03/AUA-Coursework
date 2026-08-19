package com.example.event_management.repository;

import com.example.event_management.dto.PerformanceDTO;
import com.example.event_management.model.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PerformerRepository extends JpaRepository<Performer, Integer> {
    @Query(value = "SELECT Performer.PerformerID, Performer.Full_name, COUNT(DISTINCT Event.EventID) AS number_of_events, (COUNT(Schedule.ScheduleID)/NULLIF(COUNT(DISTINCT Event.EventID), 0)) AS avg_activities_per_event, AVG(Schedule.end_time - Schedule.start_time) AS avg_duration FROM Performer JOIN Schedule_performer ON Performer.PerformerID=Schedule_performer.PerformerID JOIN Schedule ON Schedule.ScheduleID=Schedule_performer.ScheduleID AND Schedule.eventID=Schedule_performer.EventID JOIN Event ON Event.EventID=Schedule.EventID WHERE Event.end_date<:endDate AND Event.start_date>:startDate GROUP BY Performer.PerformerID, Performer.Full_name", nativeQuery = true )
    List<PerformanceDTO> getPerformance(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
