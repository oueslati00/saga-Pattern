package com.example.demo.event;

import com.example.demo.dto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Getter
@NoArgsConstructor
public class OrderEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();
    private OrderStatus orderStatus;
    private OrderRequestDto orderRequestDto;

    public OrderEvent(OrderStatus orderStatus, OrderRequestDto orderRequestDto) {
        this.orderStatus = orderStatus;
        this.orderRequestDto = orderRequestDto;
    }
}
