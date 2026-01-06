package com.inventory.service.impl;

import com.inventory.model.*;
import com.inventory.model.dto.CustomerDto;
import com.inventory.model.dto.OrderDto;
import com.inventory.model.dto.ProductDto;
import com.inventory.model.helper.Email;
import com.inventory.repository.CustomerOrderRepository;
import com.inventory.repository.CustomerRepository;
import com.inventory.repository.OrderRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.EmailService;
import com.inventory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaginationResponseImpl paginationResponse;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;


    //same as user created in library
    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        CustomerDto customer = orderDto.getCustomer();
        Customer customer1= new Customer();
        customer1.setCustomerId(customer.getCustomerId());
        order.setCustomer(customer1);
        System.out.println(orderDto.getStatus());

        List<Product> purchasedProduct =new ArrayList<>();
        for(ProductDto p : orderDto.getProducts()){
            Product product = productRepository.findById(p.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            int orderedQty = p.getQuantity();

            if (product.getQuantity() < orderedQty)
                throw new Exception("Quantity not sufficient");

            product.setQuantity(product.getQuantity() - orderedQty);

            purchasedProduct.add(product);

        }
        order.setProducts(purchasedProduct);
        Order save = orderRepository.save(order);
        if(save!=null){
            CutomerOrder cutomerOrder = new CutomerOrder();
            cutomerOrder.setOrders(save);
            cutomerOrder.setCustomer(save.getCustomer());
          customerOrderRepository.save(cutomerOrder);

          Email email = new Email();
            email.setTo(new String[]{"gaikwadmanaliv007@gmail.com"});
            email.setHeader("Order Placed!!!");
            email.setMessage("Your order placed successfully \n Thanks for shopping with us..!!");
            emailService.sendEmail(email);
            productRepository.saveAll(purchasedProduct);
        }
        return orderDto;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        Order save = orderRepository.save(order);
        OrderDto orderDto1 = new OrderDto();
        orderDto1.setOrderId(save.getOrderId());
        return orderDto1;
    }

    @Override
    public Boolean deleteOrder(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if(byId.isPresent()){
            //restock logic
            Order order = byId.get();
            List<Product> products= order.getProducts();
            for(Product product:products){
                Optional<Product> byId1 = productRepository.findById(product.getProductId());
                byId1.get().setQuantity(byId1.get().getQuantity()+product.getQuantity());
                productRepository.save(byId1.get());
            }
            orderRepository.deleteById(id);

            return true;
        }
        return false;
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Optional<Order> byId = orderRepository.findById(orderId);
        if(byId.isPresent()){
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(byId.get().getOrderId());
            orderDto.setStatus(byId.get().getStatus());
            orderDto.setOrderDate(byId.get().getOrderDate());
            Customer customer = byId.get().getCustomer();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.getCustomerId());
            orderDto.setCustomer(customerDto);
            //list of products in one order
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
    public PaginatedResponse<OrderDto> getAllOrders(int size, int pageNo, Sort sort) {

        return null;
    }
}
