package com.ecomm.order_service.service;

import com.ecomm.order_service.model.dto.OrderDTO;
import com.ecomm.order_service.model.dto.OrderRequest;

import java.util.List;

public interface OrderService {

    public OrderDTO findByID(Long id);
    public List<OrderDTO> findByUser (Long id);
    public boolean create (OrderRequest orderRequest);
    public boolean delete (Long id);
}
