package com.example.event_management.service;

import com.example.event_management.model.entity.Venue;
import com.example.event_management.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    public Venue create(Venue venue) { return venueRepository.save(venue); }

    public Optional<Venue> getById(Integer id) {return venueRepository.findById(id);}

    public List<Venue> getAll() { return venueRepository.findAll(); }

    public Venue update(int id, Venue venue) {
        Venue existing = venueRepository.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));

        existing.setVenueName(venue.getVenueName());
        existing.setCountry(venue.getCountry());
        existing.setCity(venue.getCity());
        existing.setStreet(venue.getStreet());
        existing.setZip(venue.getZip());
        existing.setCapacity(venue.getCapacity());
        existing.setVenueType(venue.getVenueType());

        return venueRepository.save(existing);
    }

    public void delete(Integer id) { venueRepository.deleteById(id); }

}
