package com.inventory.service;

import com.inventory.model.PaginatedResponse;
import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.OrderDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    Boolean deleteCustomer(Long id);
    CustomerDto getCustomerById(Long customerId);

    PaginatedResponse<OrderDto> getAllCustomers(int size, int pageNo, Sort sort);
}
