package com.example.event_management.repository;

import com.example.event_management.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
