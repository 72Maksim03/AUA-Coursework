package com.example.event_management.controller;

import com.example.event_management.model.entity.Preferences;
import com.example.event_management.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @PostMapping
    public Preferences create(@RequestBody Preferences preference) {
        return preferenceService.create(preference);
    }

    @GetMapping("/{id}")
    public Preferences get(@PathVariable int id) {
        return preferenceService.getById(id).orElseThrow(()->new RuntimeException("Preference not found"));
    }

    @PutMapping("/{id}")
    public Preferences update(@PathVariable int id, @RequestBody Preferences preference) {
        return preferenceService.update(id, preference);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        preferenceService.delete(id);
    }

    @GetMapping
    public List<Preferences> getAll() {
        return preferenceService.getAll();
    }
}
