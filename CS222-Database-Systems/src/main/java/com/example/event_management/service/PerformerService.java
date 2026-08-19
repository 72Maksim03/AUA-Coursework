package com.example.event_management.service;

import com.example.event_management.model.entity.Performer;
import com.example.event_management.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformerService {
    @Autowired
    private PerformerRepository performerRepository;

    public Performer create(Performer performer) { return performerRepository.save(performer); }

    public Optional<Performer> getById(Integer id) {return performerRepository.findById(id);}

    public List<Performer> getAll() { return performerRepository.findAll(); }

    public Performer update(Integer id, Performer performer) {
        Performer existing = performerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performer not found"));

        existing.setFullName(performer.getFullName());
        existing.setExpertiseArea(performer.getExpertiseArea());
        existing.setPhone(performer.getPhone());
        existing.setEmail(performer.getEmail());
        existing.setSocialMedia(performer.getSocialMedia());

        return performerRepository.save(existing);
    }

    public void delete(Integer id) { performerRepository.deleteById(id); }

}
