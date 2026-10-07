package com.embarkx.blogapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // VIOLATION: No JOIN FETCH — will cause N+1
    List<Order> findByCustomerId(Long customerId);

    // VIOLATION: No fetch join on items
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatus(@Param("status") String status);
}
