package com.inventory.service;

import com.inventory.model.PaginatedResponse;
import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.OrderDto;
import com.inventory.model.dto.SupplierDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    SupplierDto createSupplier(SupplierDto supplierDto);
    SupplierDto updateSupplier(SupplierDto supplierDto);
    Boolean deleteSupplier(Long id);
    SupplierDto getSupplierById(Long supplierId);

    PaginatedResponse<SupplierDto> getAllSupplier(int size, int pageNo, Sort sort);
    List<SupplierDto> createSuppliers(List<SupplierDto> supplierDtoList);
}
