package com.example.orderservice.Service;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.event.OrderEvent;
import com.example.demo.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {


    private Sinks.Many<OrderEvent> orderSinks;

    @Autowired
    public OrderStatusPublisher(Sinks.Many<OrderEvent> orderSinks) {
        this.orderSinks = orderSinks;
    }

    public void publishOrderEvent(OrderRequestDto orderRequestDto , OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderStatus, orderRequestDto);
        orderSinks.tryEmitNext(orderEvent);
    }



}
