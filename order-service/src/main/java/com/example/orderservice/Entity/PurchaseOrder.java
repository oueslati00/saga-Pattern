package com.example.orderservice.Entity;

import com.example.demo.event.OrderStatus;
import com.example.demo.event.PaymentStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
