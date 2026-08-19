package com.example.event_management.repository;

import com.example.event_management.dto.SupplierNumDTO;
import com.example.event_management.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT * FROM Supplier WHERE product_type=:productType", nativeQuery = true)
    public List<Supplier> getSuppliersByProductType(@Param("productType") String productType);

    @Query(value = "SELECT Supplier.supplierID, Supplier.Supplier_name, COUNT(Event.EventID) AS Number_of_times_provided FROM Supplier JOIN event_supplier_equipment ON event_supplier_equipment.supplierID=Supplier.supplierID JOIN Event ON event_supplier_equipment.eventID=Event.EventID WHERE Event.end_date<:endDate AND Event.start_date>:startDate GROUP BY Supplier.SupplierID, Supplier.Supplier_name", nativeQuery = true)
    public List<SupplierNumDTO> getNumberOfTimesSuppliersProvided(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);

}
