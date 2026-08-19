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
public class SupplierEquipmentId implements Serializable {

    @Column(name = "SupplierID")
    private Integer supplierId;
    @Column(name = "EquipmentID")
    private Integer equipmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierEquipmentId)) return false;
        SupplierEquipmentId that = (SupplierEquipmentId) o;
        return supplierId.equals(that.supplierId) && equipmentId.equals(that.equipmentId);
    }

    @Override
    public int hashCode() {
        return 31 * supplierId.hashCode() + equipmentId.hashCode();
    }
}
