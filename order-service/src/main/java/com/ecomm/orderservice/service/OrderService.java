package com.ecomm.orderservice.service;

import com.ecomm.orderservice.model.dto.OrderDTO;
import com.ecomm.orderservice.model.dto.OrderRequest;

import java.util.List;

public interface OrderService {

    public OrderDTO findByID(Long id);
    public List<OrderDTO> findByUser (Long id);
    public boolean create (OrderRequest orderRequest);
    public boolean delete (Long id);
}
