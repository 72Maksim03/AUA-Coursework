package com.example.event_management.controller;

import com.example.event_management.model.entity.EventSupplierEquipment;
import com.example.event_management.model.entity.EventSupplierEquipmentId;
import com.example.event_management.service.EventSupplierEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventSupplierEquipment")
public class EventSupplierEquipmentController {
    @Autowired
    private EventSupplierEquipmentService service;

    public EventSupplierEquipmentController(EventSupplierEquipmentService service){
        this.service=service;
    }

    @PostMapping
    public EventSupplierEquipment create(@RequestBody EventSupplierEquipment ese){
        return service.createEventSupplierEquipment(ese);
    }

    @GetMapping("/{eventId}/{supplierId}/{equipmentId}")
    public EventSupplierEquipment get(@PathVariable int eventId,
                                      @PathVariable int supplierId,
                                      @PathVariable int equipmentId) {
        return service.getEventSupplierEquipment(new EventSupplierEquipmentId(eventId, supplierId, equipmentId)).orElseThrow(()->new RuntimeException("EventSupplierEquipment not found"));
    }

    @PutMapping("/{eventId}/{supplierId}/{equipmentId}")
    public EventSupplierEquipment update(@PathVariable int eventId,
                                         @PathVariable int supplierId,
                                         @PathVariable int equipmentId,
                                         @RequestBody EventSupplierEquipment ese) {
        return service.update(new EventSupplierEquipmentId(eventId, supplierId, equipmentId), ese);
    }

    @DeleteMapping("/{eventId}/{supplierId}/{equipmentId}")
    public void delete(@PathVariable int eventId,
                       @PathVariable int supplierId,
                       @PathVariable int equipmentId) {
        service.deleteEventSupplierEquipment(new EventSupplierEquipmentId(eventId, supplierId, equipmentId));
    }

    @GetMapping
    public List<EventSupplierEquipment> getAll() {
        return service.getAllEventSupplierEquipment();
    }
}
