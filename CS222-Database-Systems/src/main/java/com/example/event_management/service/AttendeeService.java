package com.example.event_management.service;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    public Attendee createAttendee(Attendee attendee){return attendeeRepository.save(attendee);}

    public Optional<Attendee> getAttendee(Integer id){return attendeeRepository.findById(id);}

    public List<Attendee> getAllAttendees(){return attendeeRepository.findAll();}

    public Attendee updateAttendee(int id, Attendee attendee){
        Attendee existing=attendeeRepository.findById(id).orElseThrow(()->new RuntimeException("Attendee not found"));

        existing.setFullName(attendee.getFullName());
        existing.setEmail(attendee.getEmail());
        existing.setPhone(attendee.getPhone());
        existing.setPreferences(attendee.getPreferences());

        return attendeeRepository.save(existing);
    }

    public void deleteAttendee(Integer id){attendeeRepository.deleteById(id);}
}
