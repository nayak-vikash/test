package com.embarkx.blogapi;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Long customerId;

    // VIOLATION: EAGER on a collection
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;

    // getters and setters
    public Long getId() { return id; }
    public List<OrderItem> getItems() { return items; }
    public String getStatus() { return status; }
    public Long getCustomerId() { return customerId; }
}
