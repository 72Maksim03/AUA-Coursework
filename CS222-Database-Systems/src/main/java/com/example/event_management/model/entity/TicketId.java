package com.example.event_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketId implements Serializable {
    @Column(name = "EventID")
    private Integer eventId;
    @Column(name = "TicketID")
    private Integer ticketId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketId)) return false;
        TicketId that = (TicketId) o;
        return eventId.equals(that.eventId) && ticketId.equals(that.ticketId);
    }

    @Override
    public int hashCode() {
        return 31 * eventId.hashCode() + ticketId.hashCode();
    }
}
