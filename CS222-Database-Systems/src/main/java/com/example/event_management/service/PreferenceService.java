package com.example.event_management.service;

import com.example.event_management.model.entity.Preferences;
import com.example.event_management.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {
    @Autowired
    private PreferenceRepository preferenceRepository;

    public Preferences create(Preferences preference) { return preferenceRepository.save(preference); }

    public Optional<Preferences> getById(Integer id) {return preferenceRepository.findById(id);}

    public List<Preferences> getAll() { return preferenceRepository.findAll(); }

    public Preferences update(int id, Preferences preference) {
        Preferences existing = preferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preference not found"));

        existing.setPreferenceType(preference.getPreferenceType());
        existing.setPreferenceValue(preference.getPreferenceValue());

        return preferenceRepository.save(existing);
    }

    public void delete(Integer id) { preferenceRepository.deleteById(id); }

}
