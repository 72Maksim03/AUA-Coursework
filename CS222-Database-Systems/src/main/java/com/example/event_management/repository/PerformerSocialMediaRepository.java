package com.example.event_management.repository;

import com.example.event_management.model.entity.PerformerSocialMedia;
import com.example.event_management.model.entity.PerformerSocialMediaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerSocialMediaRepository extends JpaRepository<PerformerSocialMedia, PerformerSocialMediaId> {
}
