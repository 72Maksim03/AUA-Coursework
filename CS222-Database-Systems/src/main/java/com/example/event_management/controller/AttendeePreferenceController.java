package com.example.event_management.controller;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.service.AttendeePreferenceService;
import com.example.event_management.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendeePreference")
public class AttendeePreferenceController {
    @Autowired
    private AttendeePreferenceService attendeePreferenceService;

    @PostMapping("/addPreference")
    public Attendee addPreference(@RequestParam int attendeeId, @RequestParam int preferenceId) {
        return attendeePreferenceService.addPreferenceToAttendee(attendeeId, preferenceId);
    }

    @DeleteMapping("/removePreference")
    public void removePreverence(@RequestParam int attendeeId, @RequestParam int preferenceId){
        attendeePreferenceService.deletePreferenceFromAttendee(attendeeId, preferenceId);
    }
}
