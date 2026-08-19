package com.example.event_management.repository;

import com.example.event_management.model.entity.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preferences, Integer> {
}
