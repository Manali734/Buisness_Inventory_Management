package com.inventory.service;

import com.inventory.model.PaginatedResponse;
import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.OrderDto;
import com.inventory.model.dto.ProductDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface ProductService
{
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    Boolean deleteProduct(Long id);
    ProductDto getProductById(Long productId);

    PaginatedResponse<ProductDto> getAllProducts(int size, int pageNo, Sort sort);
}
