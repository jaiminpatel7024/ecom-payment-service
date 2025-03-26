package com.jp.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {


    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @GetMapping("/test")
    public ResponseEntity<?> testPaymentService() {
        return ResponseEntity.ok("Payment Service running fine");
    }

    @PostMapping("/payment/create/{orderId}")
    public ResponseEntity<?> createPlan(@RequestHeader("Authorization") String token, @PathVariable("orderId") String orderId)
    {
        log.info("Received request to create payment link for orderId : {}", orderId);

        if(customerService.validateToken(token)){

            Payment paymentObj = new Payment();
            paymentObj.setPaymentId(String.valueOf(new Random().nextInt(100000)));
            paymentObj.setOrderId(orderId);
            paymentObj.setStatus("PAID");
            paymentObj.setPaymentTime(LocalDateTime.now());

            paymentRepo.save(paymentObj);

            return ResponseEntity.ok("PaymentID:"+paymentObj.getPaymentId());
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
