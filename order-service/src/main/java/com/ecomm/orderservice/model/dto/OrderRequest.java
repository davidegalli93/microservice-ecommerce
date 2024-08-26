package com.ecomm.orderservice.model.dto;

import java.util.List;

public class OrderRequest {

    private List<OrderLineItemDTO> orderLineItems;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderLineItemDTO> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItemDTO> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItemDTO> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
