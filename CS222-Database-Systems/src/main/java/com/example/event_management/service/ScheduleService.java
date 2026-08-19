package com.example.event_management.service;

import com.example.event_management.model.entity.Schedule;
import com.example.event_management.model.entity.ScheduleId;
import com.example.event_management.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule create(Schedule schedule) { return scheduleRepository.save(schedule); }

    public List<Schedule> getAll() { return scheduleRepository.findAll(); }

    public Optional<Schedule> getById(ScheduleId id) {return scheduleRepository.findById(id);}

    public Schedule update(ScheduleId id, Schedule schedule) {
        Schedule existing = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));

        existing.setEventDate(schedule.getEventDate());
        existing.setStartTime(schedule.getStartTime());
        existing.setEndTime(schedule.getEndTime());
        existing.setActivityName(schedule.getActivityName());

        return scheduleRepository.save(existing);
    }

    public void delete(ScheduleId id) { scheduleRepository.deleteById(id); }

}
