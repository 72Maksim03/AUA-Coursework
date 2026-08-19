package com.example.event_management.service;

import com.example.event_management.model.entity.EventSupplierEquipment;
import com.example.event_management.model.entity.EventSupplierEquipmentId;
import com.example.event_management.repository.EventSupplierEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventSupplierEquipmentService {
    @Autowired
    private EventSupplierEquipmentRepository repository;

    public EventSupplierEquipment createEventSupplierEquipment(EventSupplierEquipment ese){
        return repository.save(ese);
    }

    public Optional<EventSupplierEquipment> getEventSupplierEquipment(EventSupplierEquipmentId id){
        return repository.findById(id);
    }

    public List<EventSupplierEquipment> getAllEventSupplierEquipment(){
        return repository.findAll();
    }

    public EventSupplierEquipment update(EventSupplierEquipmentId id, EventSupplierEquipment ese) {
        EventSupplierEquipment existing = repository.findById(id).orElseThrow(() -> new RuntimeException("EventSupplierEquipment not found"));

        existing.setPrice(ese.getPrice());

        return repository.save(existing);
    }

    public void deleteEventSupplierEquipment(EventSupplierEquipmentId id){
        repository.deleteById(id);
    }
}
