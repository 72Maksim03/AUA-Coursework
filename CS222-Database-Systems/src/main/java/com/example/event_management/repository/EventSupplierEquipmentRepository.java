package com.example.event_management.repository;

import com.example.event_management.model.entity.EventSupplierEquipment;
import com.example.event_management.model.entity.EventSupplierEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSupplierEquipmentRepository extends JpaRepository<EventSupplierEquipment, EventSupplierEquipmentId> {
}
