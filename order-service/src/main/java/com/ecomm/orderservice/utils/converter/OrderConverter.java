package com.ecomm.orderservice.utils.converter;

import com.ecomm.orderservice.model.dto.OrderDTO;
import com.ecomm.orderservice.model.dto.OrderRequest;
import com.ecomm.orderservice.model.entity.OrderEntity;
import com.ecomm.orderservice.model.entity.OrderLineItem;
import com.ecomm.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConverter {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineItemConverter orderLineItemConverter;

    public OrderEntity toEntity (OrderDTO orderDTO){
        OrderEntity order = new OrderEntity();
        List<OrderLineItem> listProduct = orderDTO.getOrderRequest().getOrderLineItems().stream().map(orderLineItemDTO -> orderLineItemConverter.toEntity(orderLineItemDTO)).toList();
        order.setOrderLineItemList(listProduct);
        order.setDate(orderDTO.getOrderDate());
        order.setTotal(orderDTO.getOrderTotal());
        order.setId_user(orderDTO.getOrderUser());
        if (orderDTO.getOrderID() != null){
            order.setId(orderDTO.getOrderID());
        }
        return order;
    }

    public OrderDTO toDto (OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        OrderRequest orderRequest = new OrderRequest(orderEntity.getOrderLineItemList().stream().map(orderLineItem -> orderLineItemConverter.toDto(orderLineItem)).toList());
        orderDTO.setOrderRequest(orderRequest);
        orderDTO.setOrderDate(orderEntity.getDate());
        orderDTO.setOrderTotal(orderEntity.getTotal());
        orderDTO.setOrderUser(orderEntity.getId_user());
        orderDTO.setOrderID(orderEntity.getId());
        return orderDTO;
    }
}
