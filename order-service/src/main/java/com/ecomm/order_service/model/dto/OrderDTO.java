package com.ecomm.order_service.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {

    private Long orderID;
    private LocalDate orderDate;
    private Long orderUser;
    private Double orderTotal;
    private OrderRequest orderRequest;

    public OrderDTO() {
    }

    public OrderDTO(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Long orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }
}
