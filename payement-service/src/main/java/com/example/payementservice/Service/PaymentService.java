package com.example.payementservice.Service;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.PaymentRequestDto;
import com.example.demo.event.OrderEvent;
import com.example.demo.event.PaymentEvent;
import com.example.payementservice.Entity.UserBalance;
import com.example.payementservice.Entity.UserTransaction;
import com.example.payementservice.Repository.UserBalanceRepository;
import com.example.payementservice.Repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB(){
         userBalanceRepository.saveAll(Stream.of(
                new UserBalance(101, 5000),
                new UserBalance(102, 3000),
                new UserBalance(104, 2000),
                new UserBalance(105, 999)
        ).collect(Collectors.toList()));
    }

    @Autowired
    public PaymentService(UserBalanceRepository userBalanceRepository, UserTransactionRepository userTransactionRepository) {
        this.userBalanceRepository = userBalanceRepository;
        this.userTransactionRepository = userTransactionRepository;
    }
    // get the user id
    // check the balance availability
    // if balance sufficient -> Payment completed and deduct amount from DB
    // if payment not sufficient -> cancel order event and update the amount in DB
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(), orderRequestDto.getOrderId(), orderRequestDto.getAmount());

                this.userBalanceRepository.findById(orderRequestDto.getUserId())
                        .filter(ub->ub.getPrice()>orderRequestDto.getAmount())
                        .map(ub->{
                            ub.setPrice(ub.getPrice()-orderRequestDto.getAmount());
                            userBalanceRepository.save(new UserTransaction(orderRequestDto.getOrderId(),orderRequestDto.getUserId(),orderRequestDto.getAmount()))
                        })
        return null;
    }

    public void cancelOrderEvent(OrderEvent orderEvent){

    }
}
