package com.cdac.dto;

import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;

import java.util.List;

public class OrderRequestDto {

    private Order order;
    private List<OrderItem> orderItems;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

