package com.example.event_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VenueEquipmentId implements Serializable {
    @Column(name = "VenueID")
    private Integer venueId;
    @Column(name = "EquipmentID")
    private Integer equipmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueEquipmentId)) return false;
        VenueEquipmentId that = (VenueEquipmentId) o;
        return venueId.equals(that.venueId) && equipmentId.equals(that.equipmentId);
    }

    @Override
    public int hashCode() {
        return 31 * venueId.hashCode() + equipmentId.hashCode();
    }
}
