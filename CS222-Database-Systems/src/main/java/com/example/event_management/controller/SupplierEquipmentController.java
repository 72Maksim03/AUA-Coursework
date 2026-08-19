package com.example.event_management.controller;

import com.example.event_management.model.entity.SupplierEquipment;
import com.example.event_management.model.entity.SupplierEquipmentId;
import com.example.event_management.repository.SupplierEquipmentRepository;
import com.example.event_management.service.SupplierEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplierEquipment")
public class SupplierEquipmentController {
    private SupplierEquipmentService service;
    private SupplierEquipmentRepository repository;

    @Autowired
    public SupplierEquipmentController(SupplierEquipmentService service, SupplierEquipmentRepository repository){
        this.service=service;
        this.repository=repository;
    }

    @PostMapping
    public SupplierEquipment create(@RequestBody SupplierEquipment seq){
        return service.create(seq);
    }

    @GetMapping("/{supplierId}/{equipmentId}")
    public SupplierEquipment get(@PathVariable int supplierId, @PathVariable int equipmentId){
        return service.getById(new SupplierEquipmentId(supplierId, equipmentId)).orElseThrow(()->new RuntimeException("Supplier Equipment not found"));
    }

    @GetMapping
    public List<SupplierEquipment> getAll(){
        return service.getAll();
    }

    @PutMapping("/{supplierId}/{equipmentId}")
    public SupplierEquipment update(@PathVariable int supplierId, @PathVariable int equipmentId, @RequestBody SupplierEquipment seq){
        return service.update(new SupplierEquipmentId(supplierId, equipmentId), seq);
    }

    @DeleteMapping("/{supplierId}/{equipmentId}")
    public void delete(@PathVariable int supplierId, @PathVariable int equipmentId){
        service.delete(new SupplierEquipmentId(supplierId, equipmentId));
    }

    @GetMapping("/availableEquipments/{value}")
    public List<SupplierEquipment> getAvailableEquipments(@PathVariable int value){
        return repository.getAvailableEquipments(value);
    }

    @GetMapping("/availableEquipments")
    public List<SupplierEquipment> getAllAvailableEquipments(){
        return repository.getAllAvailableEquipments();
    }
}
