package com.inventory.controller;

import com.inventory.model.dto.SupplierDto;
import com.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    // Create single category
    @PostMapping("/create-supplier")
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDto supplierDto) {

        try {
            return ResponseEntity.ok(supplierService.createSupplier(supplierDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Supplier already exists");
        }
    }


    // Create list of categories
    @PostMapping("/create-supplier-list")
    public ResponseEntity<?> createSuppliers(@RequestBody List<SupplierDto> supplierDtoList) {

        try {
            return ResponseEntity.ok(supplierService.createSuppliers(supplierDtoList));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Suppliers already exist");
        }
    }


    // Fetch category by ID
    @GetMapping("/get-supplier-by-id/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long supplierId) {

        SupplierDto dto = supplierService.getSupplierById(supplierId);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Not Found");
    }


    // Pagination + Sorting
    @GetMapping("/get-all-suppliers")
    public ResponseEntity<?> getAllCategories(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy") String sortBy
    ) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        return ResponseEntity.ok(supplierService.getAllSupplier(size,pageNo,sort));
    }

    @DeleteMapping("/delete-supplier-by-id/{id}")
    public boolean deleteSupplier(@PathVariable("id") Long supplierId){
        return supplierService.deleteSupplier(supplierId);
    }

    @PutMapping("/update-supplier")
    public SupplierDto updateSupplier(@RequestBody SupplierDto dto){

        return  supplierService.updateSupplier(dto);
    }

}
