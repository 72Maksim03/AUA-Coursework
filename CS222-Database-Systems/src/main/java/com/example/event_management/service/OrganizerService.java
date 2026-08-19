package com.example.event_management.service;

import com.example.event_management.model.entity.Organizer;
import com.example.event_management.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    public Organizer create(Organizer organizer) { return organizerRepository.save(organizer); }

    public Optional<Organizer> getById(Integer id) {return organizerRepository.findById(id);}

    public List<Organizer> getAll() { return organizerRepository.findAll(); }

    public Organizer update(int id, Organizer organizer) {
        Organizer existing = organizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        existing.setName(organizer.getName());
        existing.setEmail(organizer.getEmail());
        existing.setPhone(organizer.getPhone());
        existing.setAddress(organizer.getAddress());

        return organizerRepository.save(existing);
    }

    public void delete(Integer id) { organizerRepository.deleteById(id); }

}
