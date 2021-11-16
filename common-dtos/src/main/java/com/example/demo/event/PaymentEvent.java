package com.example.demo.event;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.PaymentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PaymentEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();
    private PaymentStatus PaymentStatus;
    private PaymentRequestDto paymentRequestDto;

    public PaymentEvent(com.example.demo.event.PaymentStatus paymentStatus, PaymentRequestDto paymentRequestDto) {
        PaymentStatus = paymentStatus;
        this.paymentRequestDto = paymentRequestDto;
    }
}
