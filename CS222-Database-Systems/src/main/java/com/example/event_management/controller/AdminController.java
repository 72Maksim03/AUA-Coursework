package com.example.event_management.controller;

import com.example.event_management.model.entity.Admin;
import com.example.event_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping
    public Admin create(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @GetMapping("/{id}")
    public Admin get(@PathVariable int id){
        return adminService.getAdmin(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @PostMapping("/{id}")
    public Admin update(@PathVariable int id, @RequestBody Admin admin){
        return adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){adminService.deleteAdmin(id);}

    @GetMapping
    public List<Admin> getAll(){return adminService.getAllAdmins();}

}
