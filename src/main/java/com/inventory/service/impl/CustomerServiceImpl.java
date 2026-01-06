package com.inventory.service.impl;

import com.inventory.model.Customer;
import com.inventory.model.PaginatedResponse;
import com.inventory.model.Product;
import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.OrderDto;
import com.inventory.model.dto.ProductDto;
import com.inventory.repository.CustomerRepository;
import com.inventory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaginationResponseImpl paginationResponse;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerAddress(customerDto.getCustomerAddress());
        customer.setCustomerContact(customerDto.getCustomerContact());
        Customer save = customerRepository.save(customer);
        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(save.getCustomerId());
        return dto;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerAddress(customerDto.getCustomerAddress());
        customer.setCustomerContact(customerDto.getCustomerContact());
        Customer save = customerRepository.save(customer);
        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(save.getCustomerId());
        return dto;
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if(byId.isPresent()){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Optional<Customer> byId = customerRepository.findById(customerId);
        if(byId.isPresent()){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(byId.get().getCustomerId());
            customerDto.setCustomerName(byId.get().getCustomerName());
            customerDto.setCustomerAddress(byId.get().getCustomerAddress());
            customerDto.setCustomerEmail(byId.get().getCustomerEmail());
            customerDto.setCustomerContact(byId.get().getCustomerContact());
            //customer-order logic
            return customerDto;
        }
        return null;
    }
//    Optional<Product> byId = productRepository.findById(productId);
//        if(byId.isPresent()){
//        ProductDto dto = new ProductDto();
//        dto.setProductId(byId.get().getProductId());
//        dto.setProductName(byId.get().getProductName());
//        dto.setCategory(byId.get().getCategory());
//        dto.setDescription(byId.get().getDescription());
//        dto.setQuantity(byId.get().getQuantity());
//        dto.setProductPrice(byId.get().getProductPrice());
//        dto.setSupplier(byId.get().getSupplier());
//        return dto;
//    }
//        return null;
    @Override
    public PaginatedResponse<OrderDto> getAllCustomers(int size, int pageNo, Sort sort) {
        return null;
    }
}
