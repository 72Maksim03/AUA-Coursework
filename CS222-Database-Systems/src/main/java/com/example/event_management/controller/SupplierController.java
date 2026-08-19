package com.example.event_management.controller;

import com.example.event_management.dto.SupplierNumDTO;
import com.example.event_management.model.entity.Supplier;
import com.example.event_management.repository.SupplierRepository;
import com.example.event_management.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private SupplierService supplierService;
    private SupplierRepository repository;

    @Autowired
    public SupplierController(SupplierService supplierService, SupplierRepository repository) {
        this.supplierService = supplierService;
        this.repository=repository;
    }

    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) {
        return supplierService.create(supplier);
    }

    @GetMapping("/{id}")
    public Supplier get(@PathVariable int id) {
        return supplierService.getById(id).orElseThrow(()->new RuntimeException("Supplier not found"));
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable int id, @RequestBody Supplier supplier) {
        return supplierService.update(id, supplier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        supplierService.delete(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/productType/{value}")
    public List<Supplier> getSuppliersByProductType(@PathVariable String value){
        return repository.getSuppliersByProductType(value);
    }

    @GetMapping("/providedTimes")
    public List<SupplierNumDTO> getNumberOfTimesSuppliersProvided(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return repository.getNumberOfTimesSuppliersProvided(startDate, endDate);
    }
}
