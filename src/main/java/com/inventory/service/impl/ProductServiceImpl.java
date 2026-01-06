package com.inventory.service.impl;

import com.inventory.model.PaginatedResponse;
import com.inventory.model.Product;
import com.inventory.model.Supplier;
import com.inventory.model.dto.ProductDto;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaginationResponseImpl paginationResponse;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        //contains supplier id
        Supplier supplier = new Supplier();
        supplier.setSupplierId(productDto.getSupplier().getSupplierId());
        product.setSupplier(supplier);

        Product save = productRepository.save(product);
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId(save.getProductId());
        return productDto1;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        Product save = productRepository.save(product);
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId(save.getProductId());
        return productDto1;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> byId = productRepository.findById(productId);
        if(byId.isPresent()){
            ProductDto dto = new ProductDto();
            dto.setProductId(byId.get().getProductId());
            dto.setProductName(byId.get().getProductName());
            dto.setCategory(byId.get().getCategory());
            dto.setDescription(byId.get().getDescription());
            dto.setQuantity(byId.get().getQuantity());
            dto.setProductPrice(byId.get().getProductPrice());
            dto.setSupplier(byId.get().getSupplier());
            return dto;
        }
        return null;
    }

    @Override
    public PaginatedResponse<ProductDto> getAllProducts(int size, int pageNo, Sort sort) {
        Pageable page = PageRequest.of(pageNo, size, sort);
        Page<Product> all = productRepository.findAll(page);
        List<ProductDto> list = all.getContent().stream().map(this::bookResponse).toList();

        return paginationResponse.buildPaginatedResponse(list, all);
    }

    private ProductDto bookResponse(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setQuantity(productDto.getQuantity());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setCategory(product.getCategory());
        return productDto;
    }
}
