package com.example.payementservice.Config;

import com.example.demo.event.OrderEvent;
import com.example.demo.event.OrderStatus;
import com.example.demo.event.PaymentEvent;
import com.example.payementservice.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    private final PaymentService paymentService;

    @Autowired
    public PaymentConsumerConfig(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcess(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()-> this.paymentService.newOrderEvent(orderEvent));
        }else{
            return Mono.fromRunnable(()-> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
