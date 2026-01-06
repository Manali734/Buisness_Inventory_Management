package com.inventory.service;

import com.inventory.model.PaginatedResponse;
import com.inventory.model.dto.AppUserDto;
import com.inventory.model.dto.OrderDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto updateOrder(OrderDto orderDto);
    Boolean deleteOrder(Long id);

    OrderDto getOrderById(Long orderId);

    PaginatedResponse<OrderDto> getAllOrders(int size, int pageNo, Sort sort);
}
