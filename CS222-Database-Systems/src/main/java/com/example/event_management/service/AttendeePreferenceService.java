package com.example.event_management.service;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.entity.Preferences;
import com.example.event_management.repository.AttendeeRepository;
import com.example.event_management.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeePreferenceService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    public Attendee addPreferenceToAttendee(Integer attendeeId, Integer preferenceId){
        Attendee attendee=attendeeRepository.findById(attendeeId).orElseThrow(()->new RuntimeException("Attendee Not Found"));
        Preferences preferences=preferenceRepository.findById(preferenceId).orElseThrow(()->new RuntimeException("Preference Not Found"));

        if (!attendee.getPreferences().contains(preferences)) {
            attendee.getPreferences().add(preferences);
            attendeeRepository.save(attendee);
        }

        return attendee;
    }

    public Attendee deletePreferenceFromAttendee(Integer attendeeId, Integer preferenceId){
        Attendee attendee=attendeeRepository.findById(attendeeId).orElseThrow(()->new RuntimeException("Attendee Not Found"));
        Preferences preferences=preferenceRepository.findById(preferenceId).orElseThrow(()->new RuntimeException("Preference Not Found"));

        if (attendee.getPreferences().contains(preferences)) {
            attendee.getPreferences().remove(preferences);
            attendeeRepository.save(attendee);
        }
        return attendee;
    }
}
