package com.example.event_management.model.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ScheduleId implements Serializable {
    private int eventID;
    private int scheduleID;

    public ScheduleId(){}

    public ScheduleId(int eventID, int scheduleID){
        this.eventID=eventID;
        this.scheduleID=scheduleID;
    }
}
