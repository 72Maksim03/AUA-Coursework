package com.example.event_management.controller;

import com.example.event_management.dto.EventInfoDTO;
import com.example.event_management.dto.EventRevenueDTO;
import com.example.event_management.dto.TotalRevenueDTO;
import com.example.event_management.dto.UpcomingEventsDTO;
import com.example.event_management.model.entity.Event;
import com.example.event_management.model.enums.EventType;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private EventService eventService;
    private EventRepository eventRepository;

    @Autowired
    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public Event create(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable int id){
        return eventService.getEvent(id).orElseThrow(()->new RuntimeException("Event not found"));
    }

    @GetMapping
    public List<Event> getAll(){
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable int id, @RequestBody Event event){
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        eventService.deleteEvent(id);
    }

    @GetMapping("/type/{type}")
    public List<Event> getEventsByType(@PathVariable EventType type){
        return eventRepository.findByEventType(type.name());
    }

    @GetMapping("/date")
    public List<Event> findEventsBetween(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return eventRepository.findEventsBetween(start, end);
    }

    @GetMapping("/searchByName/{value}")
    public List<Event> findEventsByName(@PathVariable String value){
        return eventRepository.findEventByName(value);
    }

    @GetMapping("/eventRevenueByOrganizer/{id}")
    public List<EventRevenueDTO> findEventRevenueByOrganizerId(@PathVariable int id){
        return eventRepository.findEventRevenueByOrganizerId(id);
    }

    @GetMapping("/ticketSales")
    public List<EventRevenueDTO> ticketSales(){
        return eventRepository.ticketSales();
    }

    @GetMapping("/totalRevenue")
    public List<TotalRevenueDTO> totalRevenue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return eventRepository.totalRevenue(startDate, endDate);
    }

    @GetMapping("/report")
    public List<EventInfoDTO> getEventInfo(){
        return eventRepository.getEventInfo();
    }

    @GetMapping("/upcoming/{venueId}")
    public List<UpcomingEventsDTO> getUpcoming(@PathVariable int venueId){
        return eventRepository.getUpcomingEvents(venueId, LocalDate.now());
    }

    @GetMapping("/profit/{eventId}")
    public BigDecimal getProfitFromEvent(@PathVariable int eventId){
        return eventRepository.getProfitFromEvent(eventId);
    }
}
