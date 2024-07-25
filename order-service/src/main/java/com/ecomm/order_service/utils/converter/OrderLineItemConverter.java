package com.ecomm.order_service.utils.converter;

import com.ecomm.order_service.model.dto.OrderLineItemDTO;
import com.ecomm.order_service.model.entity.OrderLineItem;
import org.springframework.stereotype.Component;

@Component
public class OrderLineItemConverter {

    public OrderLineItem toEntity (OrderLineItemDTO orderLineItemDTO){
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDTO.getSkuCode());
        if(orderLineItemDTO.getId() != null){
            orderLineItem.setId(orderLineItem.getId());
        }
        return orderLineItem;
    }

    public OrderLineItemDTO toDto (OrderLineItem orderLineItem){
        OrderLineItemDTO orderLineItemDTO = new OrderLineItemDTO();
        orderLineItemDTO.setPrice(orderLineItem.getPrice());
        orderLineItemDTO.setQuantity(orderLineItem.getQuantity());
        orderLineItemDTO.setSkuCode(orderLineItem.getSkuCode());
        orderLineItemDTO.setId(orderLineItem.getId());
        return orderLineItemDTO;
    }
}
