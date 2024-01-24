package com.example.orderservice18_1_24.Repository;

import com.example.orderservice18_1_24.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
