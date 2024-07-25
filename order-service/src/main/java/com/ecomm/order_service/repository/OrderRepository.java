package com.ecomm.order_service.repository;

import com.ecomm.order_service.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {




}
