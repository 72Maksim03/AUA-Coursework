package com.example.event_management.controller;

import com.example.event_management.model.entity.Organizer;
import com.example.event_management.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService){
        this.organizerService=organizerService;
    }

    @PostMapping
    public Organizer create(@RequestBody Organizer organizer){
        return organizerService.create(organizer);
    }

    @GetMapping("/{id}")
    public Organizer get(@PathVariable int id){
        return organizerService.getById(id).orElseThrow(()->new RuntimeException("Organizer not found"));
    }

    @GetMapping
    public List<Organizer> getAll(){
        return organizerService.getAll();
    }

    @PutMapping("/{id}")
    public Organizer update(@PathVariable int id, @RequestBody Organizer organizer){
        return organizerService.update(id, organizer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        organizerService.delete(id);
    }
}
