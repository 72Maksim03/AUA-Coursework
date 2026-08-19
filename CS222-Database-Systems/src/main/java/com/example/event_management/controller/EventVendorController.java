package com.example.event_management.controller;

import com.example.event_management.model.entity.EventVendor;
import com.example.event_management.model.entity.EventVendorId;
import com.example.event_management.service.EventVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventVendor")
public class EventVendorController {
    @Autowired
    private EventVendorService eventVendorService;

    public EventVendorController(EventVendorService eventVendorService){
        this.eventVendorService=eventVendorService;
    }

    @PostMapping
    public EventVendor create(@RequestBody EventVendor eventVendor){
        return eventVendorService.createEventVendor(eventVendor);
    }

    @GetMapping("/{id}")
    public EventVendor get(@PathVariable EventVendorId id){
        return eventVendorService.getEventVendor(id).orElseThrow(()->new RuntimeException("EventVendor not found"));
    }

    @GetMapping
    public List<EventVendor> getAll(){
        return eventVendorService.getAllEventVendor();
    }

    @PutMapping("/{id}")
    public EventVendor update(@PathVariable EventVendorId id, @RequestBody EventVendor ev){
        return eventVendorService.update(id, ev);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable EventVendorId id){
        eventVendorService.delete(id);
    }
}
