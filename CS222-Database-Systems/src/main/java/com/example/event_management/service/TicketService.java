package com.example.event_management.service;

import com.example.event_management.model.entity.Ticket;
import com.example.event_management.model.entity.TicketId;
import com.example.event_management.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket create(Ticket ticket) { return ticketRepository.save(ticket); }

    public Optional<Ticket> getById(TicketId id) {return ticketRepository.findById(id);}

    public List<Ticket> getAll() { return ticketRepository.findAll(); }

    public Ticket update(TicketId id, Ticket ticket) {
        Ticket existing = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        existing.setAttendee(ticket.getAttendee());
        existing.setPurchaseDate(ticket.getPurchaseDate());
        existing.setPrice(ticket.getPrice());
        existing.setRowNumber(ticket.getRowNumber());
        existing.setSeatNumber(ticket.getSeatNumber());

        return ticketRepository.save(existing);
    }

    public void delete(TicketId id) { ticketRepository.deleteById(id); }

}
