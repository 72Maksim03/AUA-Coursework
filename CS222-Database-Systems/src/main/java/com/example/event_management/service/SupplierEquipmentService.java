package com.example.event_management.service;

import com.example.event_management.model.entity.SupplierEquipment;
import com.example.event_management.model.entity.SupplierEquipmentId;
import com.example.event_management.repository.SupplierEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierEquipmentService {
    @Autowired
    private SupplierEquipmentRepository repository;

    public SupplierEquipment create(SupplierEquipment obj) { return repository.save(obj); }

    public Optional<SupplierEquipment> getById(SupplierEquipmentId id) {return repository.findById(id);}

    public List<SupplierEquipment> getAll() { return repository.findAll(); }

    public SupplierEquipment update(SupplierEquipmentId id, SupplierEquipment obj) {
        SupplierEquipment existing=repository.findById(id).orElseThrow(()->new RuntimeException("SupplierEquipment not found"));

        existing.setEquipmentType(obj.getEquipmentType());
        existing.setCondition(obj.getCondition());
        existing.setAvailabilityStatus(obj.getAvailabilityStatus());
        existing.setDescription(obj.getDescription());
        existing.setSupplier(obj.getSupplier());

        return repository.save(existing);
    }

    public void delete(SupplierEquipmentId id) { repository.deleteById(id); }

}
