package com.example.event_management.controller;

import com.example.event_management.dto.PerformanceDTO;
import com.example.event_management.model.entity.Performer;
import com.example.event_management.repository.PerformerRepository;
import com.example.event_management.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/performer")
public class PerformerController {
    private PerformerService performerService;
    private PerformerRepository repository;

    @Autowired
    public PerformerController(PerformerService performerService, PerformerRepository repository) {
        this.performerService = performerService;
        this.repository=repository;
    }

    @PostMapping
    public Performer create(@RequestBody Performer performer) {
        return performerService.create(performer);
    }

    @GetMapping("/{id}")
    public Performer get(@PathVariable int id) {
        return performerService.getById(id).orElseThrow(()->new RuntimeException("Performer not found"));
    }

    @PutMapping("/{id}")
    public Performer update(@PathVariable int id, @RequestBody Performer performer) {
        return performerService.update(id, performer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        performerService.delete(id);
    }

    @GetMapping
    public List<Performer> getAll() {
        return performerService.getAll();
    }

    @GetMapping("/performance")
    public List<PerformanceDTO> getPerformance(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return repository.getPerformance(startDate, endDate);
    }

}
