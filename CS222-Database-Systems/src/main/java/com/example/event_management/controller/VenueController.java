package com.example.event_management.controller;

import com.example.event_management.dto.VenueStatusDTO;
import com.example.event_management.dto.VenueUsedDTO;
import com.example.event_management.dto.VenueWithNumDTO;
import com.example.event_management.model.entity.Venue;
import com.example.event_management.model.enums.VenueType;
import com.example.event_management.repository.VenueRepository;
import com.example.event_management.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {
    private VenueService service;
    private VenueRepository repository;

    @Autowired
    public VenueController(VenueService service, VenueRepository repository) {
        this.service = service;
        this.repository=repository;
    }

    @PostMapping
    public Venue create(@RequestBody Venue venue) {
        return service.create(venue);
    }

    @GetMapping("/{id}")
    public Venue get(@PathVariable int id) {
        return service.getById(id).orElseThrow(()->new RuntimeException("Venue not found"));
    }

    @PutMapping("/{id}")
    public Venue update(@PathVariable int id, @RequestBody Venue venue) {
        return service.update(id, venue);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping
    public List<Venue> getAll() {
        return service.getAll();
    }

    @GetMapping("/type/{type}")
    public List<Venue> getVenuesFromType(@PathVariable VenueType type){
        return repository.getVenuesFromType(type.name());
    }

    @GetMapping("/date")
    public List<Venue> getAvailableVenues(@RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime){
        return repository.getAvailableVenues(startDateTime, endDateTime);
    }

    @GetMapping("/dateWithStatus")
    public List<VenueStatusDTO> getVenuesWithStatus(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        return repository.getVenuesWithStatus(startDate, endDate);
    }

    @GetMapping("/usedTimes")
    public List<VenueUsedDTO> getNumberOfTimesVenuesUsed(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return repository.getNumberOfTimesVenuesUsed(startDate, endDate);
    }

    @GetMapping("/upcoming")
    public List<VenueWithNumDTO> getUpcoming(){
        return repository.getNumberOfUpcoming(LocalDate.now());
    }
}
