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
public class EventSupplierEquipmentId implements Serializable {

    @Column(name = "EventID")
    private Integer eventId;

    @Column(name = "SupplierID")
    private Integer supplierId;

    @Column(name = "EquipmentID")
    private Integer equipmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventSupplierEquipmentId)) return false;
        EventSupplierEquipmentId that = (EventSupplierEquipmentId) o;
        return eventId.equals(that.eventId)
                && supplierId.equals(that.supplierId)
                && equipmentId.equals(that.equipmentId);
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + supplierId.hashCode();
        result = 31 * result + equipmentId.hashCode();
        return result;
    }
}
