package com.ecomm.order_service.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private Long id_user;

    @Column
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_order")
    private List<OrderLineItem> orderLineItemList;

    public OrderEntity() {}

    public OrderEntity(Long id, LocalDate date, Long id_user, Double total, List<OrderLineItem> orderLineItemList) {
        this.id = id;
        this.date = date;
        this.id_user = id_user;
        this.total = total;
        this.orderLineItemList = orderLineItemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity order = (OrderEntity) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(id_user, order.id_user) && Objects.equals(total, order.total) && Objects.equals(orderLineItemList, order.orderLineItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, id_user, total, orderLineItemList);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", date=" + date +
                ", id_user=" + id_user +
                ", total=" + total +
                ", orderLineItemList=" + orderLineItemList +
                '}';
    }
}