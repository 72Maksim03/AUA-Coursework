package com.example.event_management.service;

import com.example.event_management.model.entity.VenueEquipment;
import com.example.event_management.model.entity.VenueEquipmentId;
import com.example.event_management.repository.VenueEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueEquipmentService {
    @Autowired
    private VenueEquipmentRepository repository;

    public VenueEquipment create(VenueEquipment obj) { return repository.save(obj); }

    public Optional<VenueEquipment> getById(VenueEquipmentId id) {return repository.findById(id);}

    public List<VenueEquipment> getAll() { return repository.findAll(); }

    public VenueEquipment update(VenueEquipmentId id, VenueEquipment obj) {
        VenueEquipment existing = repository.findById(id).orElseThrow(() -> new RuntimeException("VenueEquipment not found"));

        existing.setEquipmentType(obj.getEquipmentType());
        existing.setCondition(obj.getCondition());
        existing.setDescription(obj.getDescription());

        return repository.save(existing);
    }

    public void delete(VenueEquipmentId id) { repository.deleteById(id); }

}
