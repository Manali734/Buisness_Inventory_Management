package com.inventory.service.impl;

import com.inventory.model.Order;
import com.inventory.model.PaginatedResponse;
import com.inventory.model.Product;
import com.inventory.model.Supplier;
import com.inventory.model.dto.OrderDto;
import com.inventory.model.dto.ProductDto;
import com.inventory.model.dto.SupplierDto;
import com.inventory.repository.SupplierRepository;
import com.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PaginationResponseImpl paginationResponse;
    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setSupplierContact(supplierDto.getSupplierContact());
        supplier.setSupplierEmail(supplierDto.getSupplierEmail());
        supplier.setGstNumber(supplierDto.getGstNumber());
        Supplier save = supplierRepository.save(supplier);
        SupplierDto supplierDto1 = new SupplierDto();
        supplierDto1.setSupplierId(save.getSupplierId());
        return supplierDto1;
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setSupplierContact(supplierDto.getSupplierContact());
        supplier.setSupplierEmail(supplierDto.getSupplierEmail());
        supplier.setGstNumber(supplierDto.getGstNumber());
        Supplier save = supplierRepository.save(supplier);
        SupplierDto supplierDto1 = new SupplierDto();
        supplierDto1.setSupplierId(save.getSupplierId());
        return supplierDto1;
    }

    @Override
    public Boolean deleteSupplier(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if(byId.isPresent()){
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public SupplierDto getSupplierById(Long supplierId) {
        Optional<Supplier> byId = supplierRepository.findById(supplierId);
        if(byId.isPresent()){
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setSupplierId(byId.get().getSupplierId());
            supplierDto.setSupplierName(byId.get().getSupplierName());
            supplierDto.setSupplierContact(byId.get().getSupplierContact());
            supplierDto.setSupplierEmail(byId.get().getSupplierEmail());
            supplierDto.setSupplierAddress(byId.get().getSupplierAddress());
            supplierDto.setGstNumber(byId.get().getGstNumber());
            //list of products in one supplier
            List<Product> products = byId.get().getProducts();
            List<ProductDto> productDtos = new ArrayList<>();
            products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setQuantity(product.getQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setDescription(product.getDescription());
            productDtos.add(productDto);
        });
        }
        return null;
    }
    @Override
    public PaginatedResponse<SupplierDto> getAllSupplier(int size, int pageNo, Sort sort) {

        Pageable page = PageRequest.of(pageNo, size, sort);
        Page<Supplier> all = supplierRepository.findAll(page);

        List<SupplierDto> list = all.getContent().stream().map(this::supplierResponse).toList();

        return paginationResponse.buildPaginatedResponse(list, all);
    }

    @Override
    public List<SupplierDto> createSuppliers(List<SupplierDto> supplierDtoList) {
        List<Supplier> suppliers = supplierDtoList.stream().map(this::supplierRequest).toList();

        List<Supplier> savedList = supplierRepository.saveAll(suppliers);

        return savedList.stream().map(this::supplierResponse).toList();
    }

    public SupplierDto supplierResponse(Supplier supplier){
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setSupplierName(supplier.getSupplierName());
        supplierDto.setSupplierContact(supplier.getSupplierContact());
        supplierDto.setSupplierEmail(supplier.getSupplierEmail());
        supplierDto.setSupplierAddress(supplier.getSupplierAddress());
        supplierDto.setGstNumber(supplier.getGstNumber());
        return supplierDto;
    }

    public Supplier supplierRequest(SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierContact(supplierDto.getSupplierContact());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setGstNumber(supplierDto.getGstNumber());
        supplier.setSupplierEmail(supplierDto.getSupplierEmail());
        return supplier;
    }
}
