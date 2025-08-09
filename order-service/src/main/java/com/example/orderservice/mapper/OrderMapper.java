package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;

public class OrderMapper {
    public static Order mapToEntity(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderItems(orderRequest.getOrderItemDtoList().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = OrderItemMapper.mapToEntity(orderItemDto);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .toList());
        return order;
    }

    public static OrderRequest mapToDto(Order order){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderItemDtoList(order.getOrderItems().stream().map(OrderItemMapper::mapToDto).toList());
        return orderRequest;
    }
}
