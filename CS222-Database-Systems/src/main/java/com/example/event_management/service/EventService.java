package com.example.event_management.service;

import com.example.event_management.model.entity.Event;
import com.example.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){return eventRepository.save(event);}

    public Optional<Event> getEvent(Integer id){return eventRepository.findById(id);}

    public List<Event> getAllEvents(){return eventRepository.findAll();}

    public Event updateEvent(int id, Event event){
        Event existing=eventRepository.findById(id).orElseThrow(()->new RuntimeException("Event not found"));

        existing.setEventName(event.getEventName());
        existing.setEventType(event.getEventType());
        existing.setStartDate(event.getStartDate());
        existing.setEndDate(event.getEndDate());
        existing.setDescription(event.getDescription());
        existing.setTotalBudget(event.getTotalBudget());
        existing.setStatus(event.getStatus());
        existing.setVenue(event.getVenue());
        existing.setAdmin(event.getAdmin());
        existing.setOrganizer(event.getOrganizer());

        return eventRepository.save(existing);
    }

    public void deleteEvent(Integer id){eventRepository.deleteById(id);}
}
