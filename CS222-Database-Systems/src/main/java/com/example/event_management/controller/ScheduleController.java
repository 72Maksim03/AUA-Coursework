package com.example.event_management.controller;

import com.example.event_management.model.entity.Schedule;
import com.example.event_management.model.entity.ScheduleId;
import com.example.event_management.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public Schedule create(@RequestBody Schedule schedule) {
        return service.create(schedule);
    }

    @GetMapping("/{eventId}/{scheduleId}")
    public Schedule get(@PathVariable int eventId, @PathVariable int scheduleId) {
        return service.getById(new ScheduleId(eventId, scheduleId)).orElseThrow(()->new RuntimeException("Schedule not found"));
    }

    @PutMapping("/{eventId}/{scheduleId}")
    public Schedule update(@PathVariable int eventId, @PathVariable int scheduleId,
                           @RequestBody Schedule schedule) {
        return service.update(new ScheduleId(eventId, scheduleId), schedule);
    }

    @DeleteMapping("/{eventId}/{scheduleId}")
    public void delete(@PathVariable int eventId, @PathVariable int scheduleId) {
        service.delete(new ScheduleId(eventId, scheduleId));
    }

    @GetMapping
    public List<Schedule> getAll() {
        return service.getAll();
    }

}
