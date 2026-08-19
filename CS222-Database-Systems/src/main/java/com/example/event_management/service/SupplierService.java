package com.example.event_management.service;

import com.example.event_management.model.entity.Supplier;
import com.example.event_management.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier create(Supplier supplier) { return supplierRepository.save(supplier); }

    public Optional<Supplier> getById(Integer id) {return supplierRepository.findById(id);}

    public List<Supplier> getAll() { return supplierRepository.findAll(); }

    public Supplier update(int id, Supplier supplier) {
        Supplier existing = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));

        existing.setSupplierName(supplier.getSupplierName());
        existing.setProductType(supplier.getProductType());
        existing.setEmail(supplier.getEmail());
        existing.setPhone(supplier.getPhone());

        return supplierRepository.save(existing);
    }

    public void delete(Integer id) { supplierRepository.deleteById(id); }

}
