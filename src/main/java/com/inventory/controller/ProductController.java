package com.inventory.controller;

import com.inventory.model.dto.ProductDto;
import com.inventory.model.dto.SupplierDto;
import com.inventory.service.ProductService;
import com.inventory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    // Create single category
    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {

        try {
            return ResponseEntity.ok(productService.createProduct(productDto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("product already exists");
        }
    }




    // Fetch category by ID
    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productId) {

        ProductDto dto = productService.getProductById(productId);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
    }


    // Pagination + Sorting
    @GetMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @RequestParam(value = "sortBy") String sortBy
    ) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        return ResponseEntity.ok(productService.getAllProducts(size,pageNo,sort));
    }

    @DeleteMapping("/delete-product-by-id/{id}")
    public boolean deleteProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }

    @PutMapping("/update-product")
    public ProductDto updateProduct(@RequestBody ProductDto dto){

        return  productService.updateProduct(dto);
    }


}
