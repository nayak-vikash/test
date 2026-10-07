package com.embarkx.blogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    // VIOLATION: Field injection
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        // VIOLATION: N+1 — accessing items inside a loop
        for (Order order : orders) {
            System.out.println("Items count: " + order.getItems().size());
        }

        return orders;
    }

    public List<Order> getPendingOrders() {
        List<Order> orders = orderRepository.findByStatus("PENDING");

        // VIOLATION: N+1 again in a stream
        orders.stream()
                .map(order -> order.getItems().size())
                .forEach(System.out::println);

        return orders;
    }
}
