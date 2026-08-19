package com.example.event_management.controller;

import com.example.event_management.model.entity.VenueEquipment;
import com.example.event_management.model.entity.VenueEquipmentId;
import com.example.event_management.repository.VenueEquipmentRepository;
import com.example.event_management.service.VenueEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venueEquipment")
public class VenueEquipmentController {
    private VenueEquipmentService service;
    private VenueEquipmentRepository repository;

    @Autowired
    public VenueEquipmentController(VenueEquipmentService service, VenueEquipmentRepository repository) {
        this.service = service;
        this.repository=repository;
    }

    @PostMapping
    public VenueEquipment create(@RequestBody VenueEquipment ve) {
        return service.create(ve);
    }

    @GetMapping("/{venueId}/{equipmentId}")
    public VenueEquipment get(@PathVariable int venueId, @PathVariable int equipmentId) {
        return service.getById(new VenueEquipmentId(venueId, equipmentId)).orElseThrow(()->new RuntimeException("venue equipment not found"));
    }

    @PutMapping("/{venueId}/{equipmentId}")
    public VenueEquipment update(@PathVariable int venueId, @PathVariable int equipmentId,
                                 @RequestBody VenueEquipment ve) {
        return service.update(new VenueEquipmentId(venueId, equipmentId), ve);
    }

    @DeleteMapping("/{venueId}/{equipmentId}")
    public void delete(@PathVariable int venueId, @PathVariable int equipmentId) {
        service.delete(new VenueEquipmentId(venueId, equipmentId));
    }

    @GetMapping
    public List<VenueEquipment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{venueId}/equipments")
    public List<VenueEquipment> getEquipmentsInVenue(@PathVariable int venueId){
        return repository.getEquipmentsInVenue(venueId);
    }

    @GetMapping("/allEquipment")
    public List<VenueEquipment> getAllAvailableEquipment(){
        return repository.getAllAvailableEquipment();
    }
}
