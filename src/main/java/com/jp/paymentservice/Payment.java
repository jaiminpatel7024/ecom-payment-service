package com.jp.paymentservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "payments")
public class Payment {

    @Id
    private String paymentId;
    private String orderId;
    private String status; //PENDING,LINK_GENERATED,PAID,UNPAID
    private LocalDateTime paymentTime;

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", paymentTime=" + paymentTime +
                '}';
    }
}
