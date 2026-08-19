package com.example.event_management.repository;

import com.example.event_management.model.entity.Schedule;
import com.example.event_management.model.entity.ScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
}
