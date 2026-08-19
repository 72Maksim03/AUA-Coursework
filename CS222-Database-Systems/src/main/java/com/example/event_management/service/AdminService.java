package com.example.event_management.service;

import com.example.event_management.model.entity.Admin;
import com.example.event_management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin){return adminRepository.save(admin);}

    public Optional<Admin> getAdmin(Integer id){return adminRepository.findById(id);}

    public List<Admin> getAllAdmins(){return adminRepository.findAll();}

    public Admin updateAdmin(Integer id, Admin admin){
        Admin existing=adminRepository.findById(id).orElseThrow(()-> new RuntimeException("Admin not found"));

        existing.setFullName(admin.getFullName());
        existing.setEmail(admin.getEmail());
        existing.setPhone(admin.getPhone());

        return adminRepository.save(existing);
    }

    public void deleteAdmin(Integer id){adminRepository.deleteById(id);}
}
