package com.example.event_management.controller;

import com.example.event_management.model.entity.Ticket;
import com.example.event_management.model.entity.TicketId;
import com.example.event_management.repository.TicketRepository;
import com.example.event_management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService service;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketService service, TicketRepository ticketRepository) {
        this.service = service;
        this.ticketRepository=ticketRepository;
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return service.create(ticket);
    }

    @GetMapping("/{eventId}/{ticketId}")
    public Ticket get(@PathVariable int eventId, @PathVariable int ticketId) {
        return service.getById(new TicketId(eventId, ticketId)).orElseThrow(()->new RuntimeException("Ticket not found"));
    }

    @PutMapping("/{eventId}/{ticketId}")
    public Ticket update(@PathVariable int eventId, @PathVariable int ticketId,
                         @RequestBody Ticket ticket) {
        return service.update(new TicketId(eventId, ticketId), ticket);
    }

    @DeleteMapping("/{eventId}/{ticketId}")
    public void delete(@PathVariable int eventId, @PathVariable int ticketId) {
        service.delete(new TicketId(eventId, ticketId));
    }

    @GetMapping
    public List<Ticket> getAll() {
        return service.getAll();
    }

    @PostMapping("/book")
    public int bookTicket(@RequestParam int attendeeId, @RequestParam int eventId, @RequestParam int rowNumber, @RequestParam int seatNumber){
        return ticketRepository.bookTicket(attendeeId, LocalDate.now(), eventId, rowNumber, seatNumber);
    }

    @PostMapping("/cancel")
    public int cancelTicket(@RequestParam int eventId, @RequestParam int rowNumber, @RequestParam int seatNumber, @RequestParam int attendeeId){
        return ticketRepository.cancelTicket(eventId, rowNumber, seatNumber, attendeeId);
    }

    @PostMapping("/autoAssign")
    public String autoAssignTicket(@RequestParam int eventId, @RequestParam int attendeeId, @RequestParam double price){
        return ticketRepository.assignTicket(eventId, attendeeId, LocalDate.now(), price);
    }

    @PostMapping("/generate")
    public void generateTickets(@RequestParam int eventId, @RequestParam int rowNumber, @RequestParam int seatNumber){
        ticketRepository.generateSeats(eventId, rowNumber, seatNumber);
    }
}
