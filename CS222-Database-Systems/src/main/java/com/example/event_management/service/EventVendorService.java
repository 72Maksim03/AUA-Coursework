package com.example.event_management.service;

import com.example.event_management.model.entity.EventVendor;
import com.example.event_management.model.entity.EventVendorId;
import com.example.event_management.repository.EventVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventVendorService {
    @Autowired
    private EventVendorRepository repository;

    public EventVendor createEventVendor(EventVendor ev){return repository.save(ev);}

    public Optional<EventVendor> getEventVendor(EventVendorId id){return repository.findById(id);}

    public List<EventVendor> getAllEventVendor(){return repository.findAll();}

    public EventVendor update(EventVendorId id, EventVendor ev){
        EventVendor existing = repository.findById(id).orElseThrow(() -> new RuntimeException("EventVendor not found"));

        existing.setContractAmount(ev.getContractAmount());
        existing.setServiceDate(ev.getServiceDate());
        existing.setPaymentStatus(ev.getPaymentStatus());

        return repository.save(existing);

    }

    public void delete(EventVendorId id){repository.deleteById(id);}
}
