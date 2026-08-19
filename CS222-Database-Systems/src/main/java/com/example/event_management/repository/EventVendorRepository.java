package com.example.event_management.repository;

import com.example.event_management.model.entity.EventVendor;
import com.example.event_management.model.entity.EventVendorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventVendorRepository extends JpaRepository<EventVendor, EventVendorId> {
}
