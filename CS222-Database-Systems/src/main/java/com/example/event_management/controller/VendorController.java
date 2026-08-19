package com.example.event_management.controller;

import com.example.event_management.dto.VendorInfoDTO;
import com.example.event_management.model.entity.Vendor;
import com.example.event_management.repository.VendorRepository;
import com.example.event_management.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private VendorService service;
    private VendorRepository repository;

    @Autowired
    public VendorController(VendorService service, VendorRepository repository) {
        this.service = service;
        this.repository=repository;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor vendor) {
        return service.create(vendor);
    }

    @GetMapping("/{id}")
    public Vendor get(@PathVariable int id) {
        return service.getById(id).orElseThrow(()->new RuntimeException("Vendor not found"));
    }

    @PutMapping("/{id}")
    public Vendor update(@PathVariable int id, @RequestBody Vendor vendor) {
        return service.update(id, vendor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return service.getAll();
    }

    @GetMapping("/serviceType/{value}")
    public List<Vendor> getVendorsByServiceType(@PathVariable String value){
        return repository.findVendorsByServiceType(value);
    }

    @GetMapping("/info")
    public List<VendorInfoDTO> getVendorInfo(){
        return repository.getVendorInfo();
    }
}
