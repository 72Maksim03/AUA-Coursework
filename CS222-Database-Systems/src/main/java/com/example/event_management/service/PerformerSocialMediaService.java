package com.example.event_management.service;

import com.example.event_management.model.entity.PerformerSocialMedia;
import com.example.event_management.model.entity.PerformerSocialMediaId;
import com.example.event_management.repository.PerformerSocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformerSocialMediaService {
    @Autowired
    private PerformerSocialMediaRepository repository;

    public PerformerSocialMedia createPerformerSocialMedia(PerformerSocialMedia perf){return repository.save(perf);}

    public Optional<PerformerSocialMedia> getById(PerformerSocialMediaId id) {return repository.findById(id);}

    public List<PerformerSocialMedia> getAll() { return repository.findAll(); }

    public PerformerSocialMedia update(PerformerSocialMediaId id, PerformerSocialMedia obj) {
        PerformerSocialMedia existing = repository.findById(id).orElseThrow(() -> new RuntimeException("PerformerSocialMedia not found"));

        existing.setPlatform(obj.getPlatform());
        existing.setUrl(obj.getUrl());

        return repository.save(existing);
    }

    public void delete(PerformerSocialMediaId id) { repository.deleteById(id); }
}
