package com.example.orderservice.Controller;

import com.example.demo.dto.OrderRequestDto;
import com.example.orderservice.Entity.PurchaseOrder;
import com.example.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PurchaseOrder> getOrders(){
        return orderService.getAllOrders();
    }

}
