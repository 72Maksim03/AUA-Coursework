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
public class EventVendorId implements Serializable {
    private Integer eventID;
    private Integer vendorID;

    public EventVendorId(){}

    public EventVendorId(Integer eventId, Integer vendorId) {
        this.eventID = eventId;
        this.vendorID = vendorId;
    }
}
