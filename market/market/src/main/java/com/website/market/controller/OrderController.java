package com.website.market.controller;


import com.website.market.dto.AddressDto;
import com.website.market.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    @Value("${spring.kafka.enabled.}")
    private boolean kafkaEnabled;
    private final OrderProcessingService orderProcessingService;

    @PostMapping("/send")
    public void submitOrder(@RequestBody AddressDto addressDto) {
        if (kafkaEnabled) {
            orderProcessingService.sendOrderKafka(addressDto.getAddress());
        } else {
            orderProcessingService.sendOrderRest(addressDto.getAddress());
        }
    }

}
