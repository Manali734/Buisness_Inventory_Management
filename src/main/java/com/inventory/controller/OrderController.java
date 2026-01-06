package com.inventory.controller;

import com.inventory.model.dto.OrderDto;
import com.inventory.service.OrderService;
import com.inventory.service.impl.PaginationResponseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaginationResponseImpl paginationResponse;
    // Create single book
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto dto) {
        System.out.println(dto.getTotalAmount());
        try {

            return ResponseEntity.ok(orderService.createOrder(dto));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Order already exists");
        }

    }


    // Fetch book by ID
    @GetMapping("/get-order-by-id/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long orderId) {

        OrderDto dto = orderService.getOrderById(orderId);

        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
    }


    // Pagination + Sorting
//    @GetMapping("/get-all-users")
//    public ResponseEntity<?> getAllUsers(
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
//            @RequestParam(value = "direction", defaultValue = "asc") String direction,
//            @RequestParam(value = "sortBy") String sortBy
//    ) {
//
//        Sort sort = direction.equalsIgnoreCase("asc") ?
//                Sort.by(sortBy).ascending() :
//                Sort.by(sortBy).descending();
//
//        return ResponseEntity.ok(userService.getAllUsers(size,pageNo,sort));
//    }

    @DeleteMapping("/delete-order-by-id/{id}")
    public boolean deleteOrder(@PathVariable("id") Long orderId){

        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/update-order")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){

        return  orderService.updateOrder(orderDto);
    }

}
