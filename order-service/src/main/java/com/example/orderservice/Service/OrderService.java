package com.example.orderservice.Service;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.event.OrderStatus;
import com.example.orderservice.Entity.PurchaseOrder;
import com.example.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderStatusPublisher orderStatusPublisher;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusPublisher orderStatusPublisher) {
        this.orderRepository = orderRepository;
        this.orderStatusPublisher = orderStatusPublisher;
    }

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder purchaseOrder = orderRepository.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(purchaseOrder.getId());
        //Produce Kafka Event with status ORDER_CREATED
        orderStatusPublisher.publishOrderEvent(orderRequestDto,OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    public List<PurchaseOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDto dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setPrice(dto.getAmount());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }
}
