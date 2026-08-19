package com.example.event_management.repository;

import com.example.event_management.dto.VendorInfoDTO;
import com.example.event_management.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    @Query(value = "SELECT * FROM Vendor WHERE service_type=:serviceType", nativeQuery = true)
    List<Vendor> findVendorsByServiceType(@Param("serviceType") String serviceType);

    @Query(value = "SELECT Vendor.vendorID, Vendor.Company_name, Vendor.Service_type, COUNT(DISTINCT Event_vendor.VendorID), AVG(Event_vendor.contract_amount) FROM Vendor JOIN Event_vendor ON Vendor.VendorID=Event_vendor.VendorID WHERE Event_vendor.payment_status='completed' GROUP BY Vendor.vendorID, Vendor.Company_name, Vendor.Service_type", nativeQuery = true)
    List<VendorInfoDTO> getVendorInfo();
}
