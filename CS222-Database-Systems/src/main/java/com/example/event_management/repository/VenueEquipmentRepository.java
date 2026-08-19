package com.example.event_management.repository;

import com.example.event_management.model.entity.VenueEquipment;
import com.example.event_management.model.entity.VenueEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueEquipmentRepository extends JpaRepository<VenueEquipment, VenueEquipmentId> {
    @Query(value = "SELECT * FROM Venue_Equipment WHERE VenueID=:venueId", nativeQuery = true)
    List<VenueEquipment> getEquipmentsInVenue(@Param("venueId") int venueId);

    @Query(value = "SELECT Venue.VenueID, Venue.Venue_name, Venue_Equipment.EquipmentID, Venue_Equipment.equipment_type, Venue_Equipment.condition, Venue_Equipment.description FROM Venue_Equipment JOIN Venue ON Venue_Equipment.VenueID=Venue.VenueID", nativeQuery = true)
    List<VenueEquipment> getAllAvailableEquipment();
}
