package com.example.event_management.controller;

import com.example.event_management.model.entity.PerformerSocialMedia;
import com.example.event_management.model.entity.PerformerSocialMediaId;
import com.example.event_management.service.PerformerSocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performerSocialMedia")
public class PerformerSocialMediaController {
    @Autowired
    private PerformerSocialMediaService service;

    public PerformerSocialMediaController(PerformerSocialMediaService service) {
        this.service = service;
    }

    @PostMapping
    public PerformerSocialMedia create(@RequestBody PerformerSocialMedia psm) {
        return service.createPerformerSocialMedia(psm);
    }

    @GetMapping("/{performerId}/{url}")
    public PerformerSocialMedia get(@PathVariable int performerId, @PathVariable String url) {
        return service.getById(new PerformerSocialMediaId(performerId, url)).orElseThrow(()->new RuntimeException("performerSocialMedia not found"));
    }

    @PutMapping("/{performerId}/{url}")
    public PerformerSocialMedia update(@PathVariable int performerId, @PathVariable String url,
                                       @RequestBody PerformerSocialMedia psm) {
        return service.update(new PerformerSocialMediaId(performerId, url), psm);
    }

    @DeleteMapping("/{performerId}/{url}")
    public void delete(@PathVariable int performerId, @PathVariable String url) {
        service.delete(new PerformerSocialMediaId(performerId, url));
    }

    @GetMapping
    public List<PerformerSocialMedia> getAll() {
        return service.getAll();
    }
}
