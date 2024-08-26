package com.ecomm.orderservice.repository;

import com.ecomm.orderservice.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {




}
