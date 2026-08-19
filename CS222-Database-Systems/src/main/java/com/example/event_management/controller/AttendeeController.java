package com.example.event_management.controller;

import com.example.event_management.dto.ActiveAttendeeDTO;
import com.example.event_management.model.entity.Attendee;
import com.example.event_management.repository.AttendeeRepository;
import com.example.event_management.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendee")
public class AttendeeController {
    private AttendeeService attendeeService;
    private AttendeeRepository repository;

    @Autowired
    public AttendeeController(AttendeeService attendeeService, AttendeeRepository repository){
        this.attendeeService=attendeeService;
        this.repository=repository;
    }

    @PostMapping
    public Attendee create(@RequestBody Attendee attendee){
        return attendeeService.createAttendee(attendee);
    }

    @GetMapping("/{id}")
    public Attendee get(@PathVariable int id){
        return attendeeService.getAttendee(id).orElseThrow(()-> new RuntimeException("Attendee not found"));
    }

    @GetMapping
    public List<Attendee> getAll(){
        return attendeeService.getAllAttendees();
    }

    @PutMapping("/{id}")
    public Attendee update(@PathVariable int id, @RequestBody Attendee attendee){
        return attendeeService.updateAttendee(id, attendee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        attendeeService.deleteAttendee(id);
    }

    @GetMapping("/actives")
    public List<ActiveAttendeeDTO> getActiveAttendees(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return repository.getActiveAttendees(startDate, endDate);
    }
}
