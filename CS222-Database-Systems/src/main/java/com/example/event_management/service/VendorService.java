package com.example.event_management.service;

import com.example.event_management.model.entity.Vendor;
import com.example.event_management.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor create(Vendor vendor) { return vendorRepository.save(vendor); }

    public Optional<Vendor> getById(Integer id) {return vendorRepository.findById(id);}

    public List<Vendor> getAll() { return vendorRepository.findAll(); }

    public Vendor update(int id, Vendor vendor) {
        Vendor existing = vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));

        existing.setCompanyName(vendor.getCompanyName());
        existing.setServiceType(vendor.getServiceType());
        existing.setEmail(vendor.getEmail());

        return vendorRepository.save(existing);
    }

    public void delete(Integer id) { vendorRepository.deleteById(id); }
}
