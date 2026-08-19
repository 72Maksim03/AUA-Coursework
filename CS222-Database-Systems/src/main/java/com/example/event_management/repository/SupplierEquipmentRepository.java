package com.example.event_management.repository;

import com.example.event_management.model.entity.SupplierEquipment;
import com.example.event_management.model.entity.SupplierEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierEquipmentRepository extends JpaRepository<SupplierEquipment, SupplierEquipmentId> {
    @Query(value = "SELECT * FROM Supplier_Equipment WHERE SupplierID=:supplierId AND availability_status='available'", nativeQuery = true)
    public List<SupplierEquipment> getAvailableEquipments(@Param("supplierId") int supplierId);

    @Query(value = "SELECT se.* FROM Supplier_Equipment se JOIN Supplier s ON s.SupplierID = se.SupplierID WHERE se.availability_status = 'available'", nativeQuery = true)
    List<SupplierEquipment> getAllAvailableEquipments();
}
