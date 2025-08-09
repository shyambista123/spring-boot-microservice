package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItem mapToEntity(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }

    public static OrderItemDto mapToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
